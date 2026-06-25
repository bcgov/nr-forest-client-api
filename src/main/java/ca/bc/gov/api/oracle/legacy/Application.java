package ca.bc.gov.api.oracle.legacy;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/** Entry point for the NR Legacy Forest Client API application. */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "${info.app.name}",
        version = "${info.app.version}",
        description = "${info.app.description}"
    ),
    servers = {
        @Server(url = "/", description = "Default Server URL")
    }
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {

  /**
   * Starts the Spring Boot application.
   *
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
