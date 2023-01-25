package ca.bc.gov.api.oracle.legacy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import ca.bc.gov.api.oracle.legacy.AbstractTestContainerIntegrationTest;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

public class ClientControllerTest extends AbstractTestContainerIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void shouldFindByClientNumberTest() {

    ClientPublicViewDto response = webTestClient
        .get()
        .uri("/api/clients/findByClientNumber/" + "00000007")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(ClientPublicViewDto.class)
        .returnResult()
        .getResponseBody();

    assertEquals("00000007", response.clientNumber());
    assertEquals("bond", response.clientName());
    assertEquals("james", response.legalFirstName());
    assertEquals("bond", response.legalMiddleName());
    assertEquals("ACT", response.clientStatusCode());
    assertEquals("I", response.clientTypeCode());
  }

  @Test
  public void shouldNotFindByClientNumberTest() {
    webTestClient
        .get()
        .uri("/api/clients/findByClientNumber/" + "00000001")
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  public void shouldFailFindByClientNumberNonNumericClientNumberTest() {
    webTestClient
        .get()
        .uri("/api/clients/findByClientNumber/" + "abcdefgh")
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  public void shouldFindAllNonIndividualsTest() {
    List<ClientPublicViewDto> response = webTestClient
        .get()
        .uri("/api/clients/findAllNonIndividuals")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(response)
        .isNotNull()
        .isNotEmpty();

    for(ClientPublicViewDto client : response) {
      assertNotEquals("I", client.clientTypeCode());
    }
  }

  @Test
  public void shouldFindByNamesTest() {
    List<ClientPublicViewDto> response = webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder
                .path("/api/clients/findByNames")
                .queryParam("clientName", "bond")
                .queryParam("clientFirstName", "james")
                .queryParam("clientMiddleName", "bond")
                .queryParam("clientTypeCodes", "I", "A")
                .build())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(response)
        .isNotNull()
        .isNotEmpty()
        .hasSize(2);
  }

  @Test
  public void shouldFindByNamesAndReturnEmptyListTest() {
    List<ClientPublicViewDto> response = webTestClient
        .get()
        .uri(uriBuilder ->
            uriBuilder
                .path("/api/clients/findByNames")
                .queryParam("clientName", "bond")
                .queryParam("clientFirstName", "james")
                .queryParam("clientMiddleName", "bond")
                .queryParam("clientTypeCodes", "B", "U")
                .build())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ClientPublicViewDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(response)
        .isNotNull()
        .isEmpty();
  }

  @Test
  public void shouldNotFindByNamesTest() {
    webTestClient
        .get()
        .uri("/api/clients/findByNames")
        .exchange()
        .expectStatus()
        .isBadRequest();
  }
}
