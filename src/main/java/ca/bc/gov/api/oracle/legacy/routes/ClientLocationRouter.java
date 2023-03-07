package ca.bc.gov.api.oracle.legacy.routes;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import ca.bc.gov.api.oracle.legacy.handlers.ClientLocationListHandler;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class ClientLocationRouter implements BaseRouter {

  private final ClientLocationListHandler listHandler;

  @Override
  public String basePath() {
    return "/clients/{clientNumber}/locations";
  }

  @Override
  public String routeTagName() {
    return "Client Location";
  }

  @Override
  public String routeTagDescription() {
    return "FSA Client Location API, responsible for returning client location data based on client number";
  }

  @Override
  public RouterFunction<ServerResponse> routerRoute() {
    return route()
        .GET(
            StringUtils.EMPTY,
            accept(MediaType.ALL),
            listHandler::handle,
            listHandler.documentation(routeTagName()))
        .build();
  }


}
