package ca.bc.gov.api.oracle.legacy.handlers;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ClientHandler {

  private final ClientService clientService;

  public Mono<ServerResponse> findByClientNumber(ServerRequest serverRequest) {
    String clientNumber = serverRequest.pathVariable("clientNumber");
    return clientService
        .findByClientNumber(clientNumber)
        .flatMap(response -> ServerResponse.ok()
            .body(Mono.just(response), ClientPublicViewDto.class));
  }

  public Mono<ServerResponse> findAllNonIndividuals(ServerRequest serverRequest) {
    int page = Integer.parseInt(serverRequest.queryParam("page").orElse("0"));
    int size = Integer.parseInt(serverRequest.queryParam("size").orElse("10"));
    String sortedColumnName = serverRequest.queryParam("sortedColumnName").orElse("clientName");

    return
        clientService.findAllNonIndividualClients(page, size, sortedColumnName)
            .flatMap(response -> ServerResponse.ok()
                .body(Mono.just(response), List.class));
  }

  public Mono<ServerResponse> findByNames(ServerRequest serverRequest) {
    String clientName = serverRequest.queryParam("clientName").orElse("");
    String clientFirstName = serverRequest.queryParam("clientFirstName").orElse("");
    String clientMiddleName = serverRequest.queryParam("clientMiddleName").orElse("");
    List<String> clientTypeCodes = serverRequest.queryParams().get("clientTypeCodes");
    int page = Integer.parseInt(serverRequest.queryParam("page").orElse("0"));
    int size = Integer.parseInt(serverRequest.queryParam("size").orElse("10"));

    return clientService.searchByNames(clientName, clientFirstName, clientMiddleName,
            clientTypeCodes, page, size)
        .flatMap(response -> ServerResponse.ok()
            .body(Mono.just(response), List.class));
  }
}
