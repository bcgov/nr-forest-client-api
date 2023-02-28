package ca.bc.gov.api.oracle.legacy.configuration;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;

import ca.bc.gov.api.oracle.legacy.routes.BaseRouter;
import io.swagger.v3.oas.models.tags.Tag;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@Slf4j
public class RouteConfiguration {

  @Bean
  public RouterFunction<ServerResponse> routes(List<BaseRouter> routes) {
    return
        routes
            .stream()
            .map(route -> nest(
                path(String.format("/api%s", route.basePath())),
                route.routerRoute())
            )
            .reduce(RouterFunction::and)
            .orElseThrow();
  }

  @Bean
  public OpenApiCustomizer tagCustomizer(List<BaseRouter> routes) {
    return openAPI -> routes.forEach(route ->
        openAPI
            .addTagsItem(
                new Tag()
                    .name(route.routeTagName())
                    .description(route.routeTagDescription())));
  }
}
