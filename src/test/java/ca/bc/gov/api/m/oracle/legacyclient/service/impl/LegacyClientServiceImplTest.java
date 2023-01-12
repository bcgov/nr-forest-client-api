package ca.bc.gov.api.m.oracle.legacyclient.service.impl;

import ca.bc.gov.api.m.oracle.legacyclient.controller.LegacyClientController;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.repository.ClientPublicViewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    	
    	//TODO: Comment out temporarily
        
        // given
    	/*given(legacyClientController.findByClientNumber(CLIENT_NUMBER)).willReturn(new ResponseEntity<Object>(client, HttpStatus.OK));

        // when
        ResponseEntity<Object> client = legacyClientServiceImpl.findByClientNumber(CLIENT_NUMBER);

        // then
        assertThat(client).isNotNull();*/
    }
    


}
