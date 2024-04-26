package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.AbstractTestContainerIntegrationTest;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("Integration Test | Client Search Handler")
class ClientSearchControllerIntegrationTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;

  @ParameterizedTest
  @MethodSource("searchById")
  @DisplayName("Search clients by ID")
  void shouldSearchClientsById(Integer returnSize, Object[] ids) {

    System.out.printf("returnSize: %d, ids: %s%n", returnSize, ids);

    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/api/clients/search")
            .queryParam("id", ids)
            .build()
        )
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .hasSize(returnSize);
  }


  private static Stream<Arguments> searchById() {
    return Stream.of(
        Arguments.of(1, new Object[]{"00000001"}),
        Arguments.of(1, new Object[]{"00000001", "1", "4"}),
        Arguments.of(0, new Object[]{"00000999"}),
        Arguments.of(0, null)
    );
  }

}