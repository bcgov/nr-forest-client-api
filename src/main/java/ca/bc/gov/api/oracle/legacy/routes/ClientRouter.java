package ca.bc.gov.api.oracle.legacy.routes;

import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import ca.bc.gov.api.oracle.legacy.handlers.ClientAcronymHandler;
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

  private final ClientAcronymHandler clientAcronymHandler;

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
    return "FSA Client API that provides data for the client details";
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
            clientAcronymHandler::handle,
            clientAcronymHandler.documentation(routeTagName()))
        .build();
  }

}
