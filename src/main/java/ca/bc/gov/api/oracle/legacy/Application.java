package ca.bc.gov.api.oracle.legacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import reactor.netty.http.server.HttpServer;
import reactor.netty.resources.LoopResources;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
    title = "${info.app.name}",
    version = "${info.app.version}",
    description = "${info.app.description}"),
    servers = {
        @Server(url = "/", description = "Default Server URL")
    }
)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    
    LoopResources loopResources = LoopResources.create("http", 4, 8, true);

    HttpServer server = HttpServer.create()
        .runOn(loopResources, false)
        .port(8080);

    server.bindNow()
          .onDispose()
          .block();
  }
}