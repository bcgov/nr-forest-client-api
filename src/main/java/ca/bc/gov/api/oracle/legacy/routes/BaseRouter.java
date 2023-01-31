package ca.bc.gov.api.oracle.legacy.routes;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

public interface BaseRouter {
  String basePath();

  RouterFunction<ServerResponse> routerRoute();

  String routeTagName();

  String routeTagDescription();

}
