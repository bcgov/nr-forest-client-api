package ca.bc.gov.api.m.oracle.legacyclient.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.bc.gov.api.m.oracle.legacyclient.controller.LegacyClientController;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.repository.ClientPublicViewRepository;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

@ExtendWith(MockitoExtension.class)
public class LegacyClientServiceImplTest {

    public static final String CLIENT_NUMBER = "00000008";
    public static final String CLIENT_NUMBER_INVALID = "abc";
    
    @Mock
    private ClientPublicViewRepository clientPublicViewRepository;

    @Mock
    private LegacyClientController legacyClientController;

    @InjectMocks
    private LegacyClientServiceImpl legacyClientServiceImpl;


    private ClientPublicViewEntity client;


    @BeforeEach
    public void setup() throws Exception {
        client = new ClientPublicViewEntity();
        client.setClientNumber("00000008");
        client.setClientName("MyOrgName");
        client.setClientStatusCode("ACT");
        client.setClientTypeCode("I");
        client.setLegalFirstName("MyFirstName");
        client.setLegalMiddleName("MyMiddleName");
    }

    @Test
    public void testFindByClientNumberPass() {
        
        // given
        given(clientPublicViewRepository.findByClientNumber(CLIENT_NUMBER)).willReturn(client);

        // when
        ClientPublicViewVO client = legacyClientServiceImpl.findByClientNumber(CLIENT_NUMBER);

        // then
        assertThat(client).isNotNull();
    }

}
