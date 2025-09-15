package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.AbstractTestContainerIntegrationTest;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
  void shouldSearchClientsById(Integer returnSize, List<String> ids) {

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

  @ParameterizedTest
  @MethodSource("searchByNameAcronymNumber")
  @DisplayName("Search clients by name, acronym, or number")
  void shouldSearchByNameAcronymOrNumber(Integer returnSize, String name, String acronym, String number) {
    webTestClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/api/clients/search/by")
            .queryParamIfPresent("name", Optional.ofNullable(name))
            .queryParamIfPresent("acronym", Optional.ofNullable(acronym))
            .queryParamIfPresent("number", Optional.ofNullable(number))
            .build()
        )
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .hasSize(returnSize);
  }


  private static Stream<Arguments> searchByNameAcronymNumber() {
    return Stream.of(
        Arguments.of(1, "INDIA",null,null),
        Arguments.of(8, null,"SAMPLIBC",null),
        Arguments.of(1, null,null,"00000001"),
        Arguments.of(1, null,null,"1"),

        Arguments.of(0, "XXAABBDA",null,null),
        Arguments.of(0, null,"XXAABB",null),
        Arguments.of(0, null,null,"12345678")
    );
  }


  private static Stream<Arguments> searchById() {
    return Stream.of(
        Arguments.of(1, List.of("00000001")),
        Arguments.of(1, List.of("00000001", "1", "4")),
        Arguments.of(0, List.of("00000999"))
    );
  }

}