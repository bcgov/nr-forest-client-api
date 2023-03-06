package ca.bc.gov.api.oracle.legacy.handlers;

import static org.springdoc.core.fn.builders.apiresponse.Builder.responseBuilder;
import static org.springdoc.core.fn.builders.arrayschema.Builder.arraySchemaBuilder;
import static org.springdoc.core.fn.builders.content.Builder.contentBuilder;
import static org.springdoc.core.fn.builders.parameter.Builder.parameterBuilder;
import static org.springdoc.core.fn.builders.requestbody.Builder.requestBodyBuilder;
import static org.springdoc.core.fn.builders.schema.Builder.schemaBuilder;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.exception.NoSearchParameterFound;
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
public class ClientAcronmHandler implements BaseHandler {

  private final ClientService service;

  @Override
  public Mono<ServerResponse> handle(ServerRequest serverRequest) {
    return
        ServerResponse
            .ok()
            .body(
                service.searchByAcronym(
                    serverRequest
                        .queryParam("acronym")
                        .orElseThrow(() -> new NoSearchParameterFound("acronym"))
                ),
                ClientPublicViewDto.class
            )
            .doOnError(ResponseStatusException.class, HandlerUtil.handleStatusResponse())
            .doOnError(HandlerUtil.handleError());
  }

  @Override
  public Consumer<Builder> documentation(String tag) {
    return ops -> ops
        .tag(tag)
        .description("Search a client by it's acronym")
        .beanClass(ClientAcronmHandler.class)
        .beanMethod("handle")
        .operationId("handle")
        .requestBody(requestBodyBuilder())
        .parameter(
            parameterBuilder()
                .description("The acronym to look for")
                .allowEmptyValue(false)
                .required(true)
                .example("Baxter")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("acronym")
        )
        .response(
            responseBuilder()
                .responseCode("200")
                .description("Returns a client data")
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
        )
        .response(
            responseBuilder()
                .responseCode("400")
                .description("If no parameter is provided")
                .content(
                    contentBuilder()
                        .schema(
                            schemaBuilder()
                                .implementation(String.class)
                                .example("No acronym parameter found")
                        )
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)
                )
        )
        .response(
            responseBuilder()
                .responseCode("404")
                .description("If no client found for that acronym")
                .content(
                    contentBuilder()
                        .schema(
                            schemaBuilder()
                                .implementation(String.class)
                                .example("No client found with the acronym Baxter")
                        )
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)
                )
        );
  }
}
