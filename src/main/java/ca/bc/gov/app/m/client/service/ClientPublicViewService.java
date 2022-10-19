package ca.bc.gov.app.m.client.service;

import ca.bc.gov.app.m.client.vo.ClientPublicVO;

public interface ClientPublicViewService {
	
	String BEAN_NAME = "clientPublicViewService";

	ClientPublicVO findByNumber();

}
