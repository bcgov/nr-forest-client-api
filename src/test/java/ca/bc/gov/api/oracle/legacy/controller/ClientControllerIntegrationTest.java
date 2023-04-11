package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.AbstractTestContainerIntegrationTest;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

@DisplayName("Integration Test | Client Handler")
class ClientControllerIntegrationTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  @DisplayName("Search client by client number")
  void shouldFindByClientNumberTest() {

    webTestClient
        .get()
        .uri("/api/clients/findByClientNumber/{clientNumber}", "00000007")
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.clientNumber").isNotEmpty()
        .jsonPath("$.clientNumber").isEqualTo("00000007")

        .jsonPath("$.clientName").isNotEmpty()
        .jsonPath("$.clientName").isEqualTo("bond")

        .jsonPath("$.legalFirstName").isNotEmpty()
        .jsonPath("$.legalFirstName").isEqualTo("james")

        .jsonPath("$.legalMiddleName").isNotEmpty()
        .jsonPath("$.legalMiddleName").isEqualTo("bond")

        .jsonPath("$.clientStatusCode").isNotEmpty()
        .jsonPath("$.clientStatusCode").isEqualTo("ACT")

        .jsonPath("$.clientTypeCode").isNotEmpty()
        .jsonPath("$.clientTypeCode").isEqualTo("I");
  }

  @Test
  @DisplayName("Did not find a client by number")
  void shouldNotFindByClientNumberTest() {
    webTestClient
        .get()
        .uri("/api/clients/findByClientNumber/{clientNumber}", "00000099")
        .exchange()
        .expectStatus().isNotFound();
  }

  @Test
  @DisplayName("Fail with non-numeric client")
  void shouldFailFindByClientNumberNonNumericClientNumberTest() {
    webTestClient
        .get()
        .uri("/api/clients/findByClientNumber/{clientName}", "abcdefgh")
        .exchange()
        .expectStatus().isBadRequest();
  }

  @Test
  @DisplayName("Non Individual clients")
  void shouldFindAllNonIndividualsTest() {
    webTestClient
        .get()
        .uri("/api/clients/findAllNonIndividuals")
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .hasSize(7);
  }

  @Test
  @DisplayName("Find by parameters")
  void shouldFindByNamesTest() {
    webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder
                .path("/api/clients/findByNames")
                .queryParam("clientName", "bond")
                .queryParam("clientFirstName", "james")
                .queryParam("clientMiddleName", "bond")
                .queryParam("clientTypeCodes", "I", "A")
                .build()
        )
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .hasSize(1);
  }

  @Test
  @DisplayName("Empty list return")
  void shouldFindByNamesAndReturnEmptyListTest() {
    webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder
                .path("/api/clients/findByNames")
                .queryParam("clientName", "band")
                .queryParam("clientFirstName", "james")
                .queryParam("clientMiddleName", "bond")
                .queryParam("clientTypeCodes", "B", "U")
                .build()
        )
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .hasSize(0);
  }

  @Test
  @DisplayName("Should not search without param")
  void shouldNotFindByNamesTest() {
    webTestClient
        .get()
        .uri("/api/clients/findByNames")
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  @DisplayName("Lookup for acronyms")
  void shouldSearchForAcronym(){

    webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder
                .path("/api/clients/findByAcronym")
                .queryParam("acronym", "DOUG")
                .build()
        )
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$[0].clientNumber").isNotEmpty()
        .jsonPath("$[0].clientNumber").isEqualTo("00000002")

        .jsonPath("$[0].clientName").isNotEmpty()
        .jsonPath("$[0].clientName").isEqualTo("FUNNY")

        .jsonPath("$[0].legalFirstName").isNotEmpty()
        .jsonPath("$[0].legalFirstName").isEqualTo("THOMAS")

        .jsonPath("$[0].legalMiddleName").isNotEmpty()
        .jsonPath("$[0].legalMiddleName").isEqualTo("Yansi")

        .jsonPath("$[0].clientStatusCode").isNotEmpty()
        .jsonPath("$[0].clientStatusCode").isEqualTo("ACT")

        .jsonPath("$[0].clientTypeCode").isNotEmpty()
        .jsonPath("$[0].clientTypeCode").isEqualTo("I");

    //

  }
}

