package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.AbstractTestContainerIntegrationTest;
import ca.bc.gov.api.oracle.legacy.dto.ClientLocationDto;
import java.net.URI;
import java.util.function.Function;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriBuilder;

@DisplayName("Integration Test | Client Location Handler")
class ClientLocationControllerIntegrationTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;

  @ParameterizedTest(name = "Client {0} in page {1} size {2} should give status {3}")
  @MethodSource("locationsClients")
  @DisplayName("Search locations by client")
  void shouldListLocationsByClient(
      String clientNumber,
      Integer page,
      Integer size,
      HttpStatus status,
      Integer returnSize
  ) {

    Function<UriBuilder, URI> uri = uriBuilder -> {

      UriBuilder localBuilder = uriBuilder
          .path("/api/clients/{clientNumber}/locations");

      if (page != null) {
        localBuilder = localBuilder.queryParam("page", page);
      }
      if (size != null) {
        localBuilder = localBuilder.queryParam("size", size);
      }

      return localBuilder.build(clientNumber);
    };

    webTestClient
        .get()
        .uri(uri)
        .exchange()
        .expectStatus().isEqualTo(status)
        .expectHeader().valueEquals("X-DATA-TOTAL", returnSize.toString())
        .expectBodyList(ClientLocationDto.class)
        .hasSize(returnSize);
  }

  @Test
  void shouldGetDetailsFromLocation() {
    webTestClient
        .get()
        .uri("/api/clients/00000001/locations/00")
        .exchange()
        .expectStatus().isEqualTo(HttpStatus.OK)
        .expectBody(ClientLocationDto.class)
        .value(clientLocationDto -> clientLocationDto.clientNumber().equals("00000001"));
  }

  private static Stream<Arguments> locationsClients() {
    return
        Stream.of(
            Arguments.of("00000001", null, null, HttpStatus.OK, 1),
            Arguments.of("00000001", 0, 10, HttpStatus.OK, 1),
            Arguments.of("00000001", 10, 10, HttpStatus.OK, 0),
            Arguments.of("00000099", null, null, HttpStatus.OK, 0)
        );
  }
}
