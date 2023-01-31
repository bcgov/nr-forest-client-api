package ca.bc.gov.api.oracle.legacy.routes;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;
import static org.springdoc.webflux.core.fn.SpringdocRouteBuilder.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.handlers.ClientHandler;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import java.util.List;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
@RequiredArgsConstructor
public class ClientRouter implements BaseRouter {

  private final ClientHandler clientHandler;

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
            clientHandler::findByClientNumber,
            clientNumberOps())
        .GET(
            "/findAllNonIndividuals",
            accept(MediaType.ALL),
            clientHandler::findAllNonIndividuals,
            nonIndividualOps())
        .GET(
            "/findByNames",
            accept(MediaType.ALL),
            clientHandler::findByNames,
            nameOps())
        .build();
  }

  private Consumer<Builder> clientNumberOps() {
    return ops -> ops
        .tag(routeTagName())
        .description("Search clients by client number")
        .beanClass(ClientHandler.class)
        .beanMethod("findByClientNumber")
        .operationId("findByClientNumber")
        .requestBody(requestBodyBuilder())
        .parameter(
            parameterBuilder()
                .description("client number to search by")
                .allowEmptyValue(false)
                .example("12345678")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.PATH)
                .name("clientNumber"))
        .response(
            responseBuilder()
                .responseCode("200")
                .description("OK")
                .content(
                    contentBuilder()
                        .schema(
                            schemaBuilder()
                                .name("ClientPublicViewDto")
                                .implementation(ClientPublicViewDto.class))
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)));

  }

  private Consumer<Builder> nonIndividualOps() {
    return ops -> ops
        .tag(routeTagName())
        .description("Return non-individual clients")
        .beanClass(ClientHandler.class)
        .beanMethod("findAllNonIndividuals")
        .operationId("findAllNonIndividuals")
        .requestBody(requestBodyBuilder())
        .parameter(
            parameterBuilder()
                .description("Name of the column to be sorted by")
                .allowEmptyValue(true)
                .example("clientName")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.PATH)
                .name("sortedColumnName"))
        .parameter(
            parameterBuilder()
                .description("Page")
                .allowEmptyValue(true)
                .example("0")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("page"))
        .parameter(
            parameterBuilder()
                .description("Page size")
                .allowEmptyValue(true)
                .example("10")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("size"))
        .response(
            responseBuilder()
                .responseCode("200")
                .description("OK")
                .content(
                    contentBuilder()
                        .schema(
                            schemaBuilder()
                                .name("ClientPublicViewDto")
                                .implementation(List.class))
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)));
  }

  private Consumer<Builder> nameOps() {
    return ops -> ops
        .tag(routeTagName())
        .description("Search client by names")
        .beanClass(ClientHandler.class)
        .beanMethod("findByNames")
        .operationId("findByNames")
        .requestBody(requestBodyBuilder())
        .parameter(
            parameterBuilder()
                .description("The name of the entity or individual's last name")
                .allowEmptyValue(true)
                .example("Baxter")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("clientName"))
        .parameter(
            parameterBuilder()
                .description("The client's first name")
                .allowEmptyValue(true)
                .example("James")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("clientFirstName"))
        .parameter(
            parameterBuilder()
                .description("The client's middle name")
                .allowEmptyValue(true)
                .example("Canter")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("clientMiddleName"))
        .parameter(
            parameterBuilder()
                .description(
                    "The type of client, can be any of the following (multiple values are allowed):\n" +
                        "A (Association)\n" +
                        "B (First Nation Band)\n" +
                        "C (Corporation)\n" +
                        "F (Ministry of Forests and Range)\n" +
                        "G (Government)\n" +
                        "I (Individual)\n" +
                        "L (Limited Partnership)\n" +
                        "P (General Partnership)\n" +
                        "R (First Nation Group)\n" +
                        "S (Society)\n" +
                        "T (First Nation Tribal Council)\n" +
                        "U (Unregistered Company)")
                .allowEmptyValue(true)
                .example("I")
                .schema(schemaBuilder().implementation(List.class))
                .in(ParameterIn.QUERY)
                .name("clientTypeCodes"))
        .parameter(
            parameterBuilder()
                .description("Page")
                .allowEmptyValue(true)
                .example("0")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("page"))
        .parameter(
            parameterBuilder()
                .description("Page size")
                .allowEmptyValue(true)
                .example("10")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("size"))
        .response(
            responseBuilder()
                .responseCode("200")
                .description("OK")
                .content(
                    contentBuilder()
                        .schema(
                            schemaBuilder()
                                .name("ClientPublicViewDto")
                                .implementation(List.class))
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)));
  }
}
