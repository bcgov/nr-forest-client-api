package ca.bc.gov.api.oracle.legacy;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.LogMessageWaitStrategy;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@ExtendWith({SpringExtension.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public abstract class AbstractTestContainerIntegrationTest {

  static final GenericContainer database;

  static {
    database = new GenericContainer("gvenzl/oracle-free:23.3-slim-faststart")
        .withEnv("ORACLE_RANDOM_PASSWORD","yes")
        .withEnv("APP_USER","THE")
        .withEnv("APP_USER_PASSWORD",UUID.randomUUID().toString().substring(24))
        .withExposedPorts(1521)
        .waitingFor(
            new LogMessageWaitStrategy()
                .withRegEx(".*DATABASE IS READY TO USE.*")
                .withStartupTimeout(Duration.of(15, ChronoUnit.MINUTES))
        );
    database.start();
  }

  @DynamicPropertySource
  static void registerDynamicProperties(DynamicPropertyRegistry registry) {

    registry
        .add(
            "ca.bc.gov.nrs.oracle.database",
            () -> "THE");

    registry
        .add(
            "ca.bc.gov.nrs.oracle.service",
            () -> "FREEPDB1"
        );

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
            () -> database.getEnvMap().get("APP_USER"));

    registry
        .add(
            "ca.bc.gov.nrs.oracle.schema",
            () -> database.getEnvMap().get("APP_USER")
        );

    registry
        .add(
            "ca.bc.gov.nrs.oracle.password",
            () -> database.getEnvMap().get("APP_USER_PASSWORD"));
  }
}
