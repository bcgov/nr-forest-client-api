package ca.bc.gov.app.m.client.service.impl;

import org.springframework.stereotype.Service;

import ca.bc.gov.app.m.client.service.ClientPublicViewService;
import ca.bc.gov.app.m.client.vo.ClientPublicVO;

@Service(ClientPublicViewService.BEAN_NAME)
public class ClientPublicViewServiceImpl implements ClientPublicViewService {

	@Override
	public ClientPublicVO findByNumber() {
		ClientPublicVO clientPublicVO = new ClientPublicVO();
		clientPublicVO.clientName = "Test";
		return clientPublicVO;
	}

}
