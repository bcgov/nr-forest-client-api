package ca.bc.gov.api.m.oracle.legacyclient.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

@ExtendWith(MockitoExtension.class)
public class LegacyClientControllerTest {

    public static final String CLIENT_NUMBER = "00000008";
    public static final String CLIENT_NUMBER_INVALID = "abc";

    @Mock
    private LegacyClientController legacyClientController;


    private ClientPublicViewEntity client;
    private ClientPublicViewVO clientVO;


    @BeforeEach
    public void setup() throws Exception {
        client = new ClientPublicViewEntity();
        client.setClientNumber("00000008");
        client.setClientName("MyOrgName");
        client.setClientStatusCode("ACT");
        client.setClientTypeCode("I");
        client.setLegalFirstName("MyFirstName");
        client.setLegalMiddleName("MyMiddleName");
        clientVO = new ClientPublicViewVO(
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
        given(legacyClientController.findByClientNumber(CLIENT_NUMBER)).willReturn(new ResponseEntity<Object>(clientVO, HttpStatus.OK));

        // when
        ResponseEntity client = legacyClientController.findByClientNumber(CLIENT_NUMBER);

        // then
        assertThat(client).isNotNull();
    }

    @Test
    public void testFindByClientNumberInvalidInput() {
        // todo: check if this is the correct way to test error cases
        
        // given
        given(legacyClientController.findByClientNumber(CLIENT_NUMBER_INVALID)).willReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        // when
        ResponseEntity response = legacyClientController.findByClientNumber(CLIENT_NUMBER_INVALID);

        // then
        assertThat(response).isNotNull();
    }

}
