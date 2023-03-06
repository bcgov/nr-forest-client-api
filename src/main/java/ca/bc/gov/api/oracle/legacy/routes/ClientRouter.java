package ca.bc.gov.api.oracle.legacy.routes;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import ca.bc.gov.api.oracle.legacy.handlers.ClientAcronmHandler;
import ca.bc.gov.api.oracle.legacy.handlers.ClientFindByNamesHandler;
import ca.bc.gov.api.oracle.legacy.handlers.ClientFindByNumberHandler;
import ca.bc.gov.api.oracle.legacy.handlers.ClientFindNonIndividualsHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class ClientRouter implements BaseRouter {

  private final ClientFindNonIndividualsHandler clientFindNonIndividualsHandler;
  private final ClientFindByNumberHandler clientFindByNumberHandler;

  private final ClientFindByNamesHandler clientFindByNamesHandler;

  private final ClientAcronmHandler clientAcronmHandler;

  @Override
  public String basePath() {
    return "/clients";
  }

  @Override
  public String routeTagName() {
    return "Client";
  }

  @Override
  public String routeTagDescription() {
    return "The FSA Client API endpoint, responsible for returning client data";
  }

  @Override
  public RouterFunction<ServerResponse> routerRoute() {
    return route()
        .GET(
            "/findByClientNumber/{clientNumber}",
            accept(MediaType.ALL),
            clientFindByNumberHandler::handle,
            clientFindByNumberHandler.documentation(routeTagName()))
        .GET(
            "/findAllNonIndividuals",
            accept(MediaType.ALL),
            clientFindNonIndividualsHandler::handle,
            clientFindNonIndividualsHandler.documentation(routeTagName()))
        .GET(
            "/findByNames",
            accept(MediaType.ALL),
            clientFindByNamesHandler::handle,
            clientFindByNamesHandler.documentation(routeTagName()))
        .GET(
            "/findByAcronym",
            accept(MediaType.ALL),
            clientAcronmHandler::handle,
            clientAcronmHandler.documentation(routeTagName()))
        .build();
  }

}
