package ca.bc.gov.api.oracle.legacy.handlers;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
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
public class ClientFindByNumberHandler implements BaseHandler {

  private final ClientService clientService;

  @Override
  public Mono<ServerResponse> handle(ServerRequest serverRequest) {
    return ServerResponse
        .ok()
        .contentType(serverRequest.headers().contentType().orElse(MediaType.APPLICATION_JSON))
        .body(
            clientService.findByClientNumber(serverRequest.pathVariable("clientNumber")),
            ClientPublicViewDto.class
        )
        .doOnError(ResponseStatusException.class, HandlerUtil.handleStatusResponse())
        .doOnError(HandlerUtil.handleError());
  }

  @Override
  public Consumer<Builder> documentation(String tag) {
    return ops -> ops
        .tag(tag)
        .description("Search clients by client number")
        .beanClass(ClientFindByNumberHandler.class)
        .beanMethod("handle")
        .operationId("handle")
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
                                .name("ClientView")
                                .implementation(ClientPublicViewDto.class))
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)
                )
        )
        .response(
            responseBuilder()
                .responseCode("400")
                .description("Client number is invalid, usually not a numeric value")
                .content(
                    contentBuilder()
                        .schema(
                            schemaBuilder()
                                .implementation(String.class)
                                .example("Invalid Client Number")
                        )
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)
                )
        )
        .response(
            responseBuilder()
                .responseCode("404")
                .description("Unable find a client based on the provided id")
                .content(
                    contentBuilder()
                        .schema(
                            schemaBuilder()
                                .implementation(String.class)
                                .example("Client not found")
                        )
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)
                )
        );
  }
}
