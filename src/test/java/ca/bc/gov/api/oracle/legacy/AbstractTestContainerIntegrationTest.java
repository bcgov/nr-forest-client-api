package ca.bc.gov.api.oracle.legacy;

import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public abstract class AbstractTestContainerIntegrationTest {

  static final OracleContainer database;

  static {
    database = new OracleContainer("gvenzl/oracle-xe:18.4.0-slim-faststart")
        .withDatabaseName("legacyfsa")
        .withUsername("THE")
        .withPassword(UUID.randomUUID().toString().substring(24));
    database.start();
  }

  @DynamicPropertySource
  static void registerDynamicProperties(DynamicPropertyRegistry registry) {

    registry
        .add(
            "ca.bc.gov.nrs.oracle.database",
            database::getDatabaseName);

    registry
        .add(
            "ca.bc.gov.nrs.oracle.service",
            database::getDatabaseName);

    registry
        .add(
            "ca.bc.gov.nrs.oracle.host",
            database::getHost);

    registry
        .add(
            "ca.bc.gov.nrs.oracle.port",
            () -> database.getMappedPort(1521));

    registry
        .add(
            "ca.bc.gov.nrs.oracle.username",
            database::getUsername);

    registry
        .add(
            "ca.bc.gov.nrs.oracle.schema",
            database::getUsername
        );

    registry
        .add(
            "ca.bc.gov.nrs.oracle.password",
            database::getPassword);
  }
}
