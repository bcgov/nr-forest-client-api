package ca.bc.gov.api.oracle.legacy.handlers;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.arrayschema.Builder.arraySchemaBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.service.ClientService;
import ca.bc.gov.api.oracle.legacy.util.HandlerUtil;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientFindNonIndividualsHandler implements BaseHandler {

  private final ClientService clientService;

  @Override
  public Mono<ServerResponse> handle(ServerRequest serverRequest) {
    int page = Integer.parseInt(serverRequest.queryParam("page").orElse("0"));
    int size = Integer.parseInt(serverRequest.queryParam("size").orElse("10"));
    String sortedColumnName = serverRequest.queryParam("sortedColumnName").orElse("clientName");

    return
        ServerResponse
            .ok()
            .contentType(serverRequest.headers().contentType().orElse(MediaType.APPLICATION_JSON))
            .body(
                clientService.findAllNonIndividualClients(page, size, sortedColumnName),
                ClientPublicViewDto.class
            )
            .doOnError(ResponseStatusException.class, HandlerUtil.handleStatusResponse())
            .doOnError(HandlerUtil.handleError());

  }

  @Override
  public Consumer<Builder> documentation(String tag) {
    return ops -> ops
        .tag(tag)
        .description("Search all non-individual client")
        .beanClass(ClientFindNonIndividualsHandler.class)
        .beanMethod("handle")
        .operationId("handle")
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
                .description("Returns a list of  client")
                .content(
                    contentBuilder()
                        .array(
                            arraySchemaBuilder()
                                .schema(
                                    schemaBuilder()
                                        .name("ClientView")
                                        .implementation(ClientPublicViewDto.class)
                                )
                        )
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)
                )
        );
  }
}
