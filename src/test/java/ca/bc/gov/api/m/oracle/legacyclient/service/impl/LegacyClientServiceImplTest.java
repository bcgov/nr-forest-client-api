package ca.bc.gov.api.m.oracle.legacyclient.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.repository.LegacyClientRepository;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

@ExtendWith(MockitoExtension.class)
public class LegacyClientServiceImplTest {

    @Mock
    private LegacyClientRepository legacyClientRepository;

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
    public void test() {
        
        // given
        given(legacyClientRepository.findByClientNumber("00000008")).willReturn(List.of(client));

        // when
        List<ClientPublicViewVO> clients = legacyClientServiceImpl.findByClientNumber("00000008");

        // then
        assertThat(clients).isNotNull();
        assertThat(clients.size()).isGreaterThan(0);
    }

}
