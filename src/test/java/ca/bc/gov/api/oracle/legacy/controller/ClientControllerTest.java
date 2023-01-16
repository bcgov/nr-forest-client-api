package ca.bc.gov.api.oracle.legacy.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ClientControllerTest {

  public static final String CLIENT_NUMBER = "00000008";
  public static final String CLIENT_NUMBER_INVALID = "abc";

  @Mock
  private ClientController clientController;


  private ClientPublicViewEntity client;
  private ClientPublicViewDto clientVO;


  @BeforeEach
  public void setup() throws Exception {
    client = new ClientPublicViewEntity();
    client.setClientNumber("00000008");
    client.setClientName("MyOrgName");
    client.setClientStatusCode("ACT");
    client.setClientTypeCode("I");
    client.setLegalFirstName("MyFirstName");
    client.setLegalMiddleName("MyMiddleName");
    clientVO = new ClientPublicViewDto(
        client.getClientNumber(),
        client.getClientName(),
        client.getLegalFirstName(),
        client.getLegalMiddleName(),
        client.getClientStatusCode(),
        client.getClientTypeCode());
  }

  @Test
  public void testFindByClientNumberPass() {

    // given
    given(clientController.findByClientNumber(CLIENT_NUMBER)).willReturn(
        new ResponseEntity<>(clientVO, HttpStatus.OK));

    // when
    ResponseEntity<ClientPublicViewDto> client = clientController.findByClientNumber(CLIENT_NUMBER);

    // then
    assertThat(client).isNotNull();
  }

  @Test
  public void testFindByClientNumberInvalidInput() {
    // todo: check if this is the correct way to test error cases

    // given
    given(clientController.findByClientNumber(CLIENT_NUMBER_INVALID)).willReturn(
        new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

    // when
    ResponseEntity<ClientPublicViewDto> response =
        clientController.findByClientNumber(CLIENT_NUMBER_INVALID);

    // then
    assertThat(response).isNotNull();
  }

}
