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
import java.util.List;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.fn.builders.operation.Builder;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientFindByNamesHandler implements BaseHandler {

  private final ClientService clientService;

  @Override
  public Mono<ServerResponse> handle(ServerRequest serverRequest) {
    return
        ServerResponse
            .ok()
            .contentType(serverRequest.headers().contentType().orElse(MediaType.APPLICATION_JSON))
            .body(
                clientService
                    .searchByNames(
                        serverRequest
                            .queryParam("clientName")
                            .orElse(StringUtils.EMPTY),
                        serverRequest
                            .queryParam("clientFirstName")
                            .orElse(StringUtils.EMPTY),
                        serverRequest
                            .queryParam("clientMiddleName")
                            .orElse(StringUtils.EMPTY),
                        serverRequest
                            .queryParam("clientTypeCodes")
                            .map(data -> data.split("\\s*,\\s*"))
                            .map(List::of)
                            .orElse(List.of()),
                        Integer
                            .parseInt(
                                serverRequest
                                    .queryParam("page")
                                    .orElse("0")
                            ),
                        Integer
                            .parseInt(
                                serverRequest
                                    .queryParam("size")
                                    .orElse("10")
                            )
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
        .description(
            "Search a client by it's name (including first, middle and last) and client type")
        .beanClass(ClientFindByNamesHandler.class)
        .beanMethod("handle")
        .operationId("handle")
        .requestBody(requestBodyBuilder())
        .parameter(
            parameterBuilder()
                .description("The name of the entity or individual's last name")
                .allowEmptyValue(true)
                .example("Baxter")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("clientName")
        )
        .parameter(
            parameterBuilder()
                .description("The client's first name")
                .allowEmptyValue(true)
                .example("James")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("clientFirstName")
        )
        .parameter(
            parameterBuilder()
                .description("The client's middle name")
                .allowEmptyValue(true)
                .example("Canter")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("clientMiddleName")
        )
        .parameter(
            parameterBuilder()
                .description("""
                    The type of client, can be any of the following:<br>
                            
                    A (Association),<br>
                    B (First Nation Band),<br>
                    C (Corporation),<br>
                    F (Ministry of Forests and Range),<br>
                    G (Government),<br>
                    I (Individual),<br>
                    L (Limited Partnership),<br>
                    P (General Partnership),<br>
                    R (First Nation Group),<br>
                    S (Society),<br>
                    T (First Nation Tribal Council),<br>
                    U (Unregistered Company)""")
                .allowEmptyValue(true)
                .example("I")
                .schema(schemaBuilder().implementation(List.class))
                .in(ParameterIn.QUERY)
                .name("clientTypeCodes")
        )
        .parameter(
            parameterBuilder()
                .description("Page")
                .allowEmptyValue(true)
                .example("0")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("page")
        )
        .parameter(
            parameterBuilder()
                .description("Page size")
                .allowEmptyValue(true)
                .example("10")
                .schema(schemaBuilder().implementation(String.class))
                .in(ParameterIn.QUERY)
                .name("size")
        )
        .response(
            responseBuilder()
                .responseCode("200")
                .description("Returns a list of  client based on provided parameters")
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
                                .example("No search parameter found")
                        )
                        .mediaType(MediaType.APPLICATION_JSON_VALUE)
                )
        );
  }
}
