package ca.bc.gov.api.m.oracle.legacyclient.service;

import org.springframework.data.domain.Page;

import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

public interface LegacyClientService {
	
	String BEAN_NAME = "legacyClientViewService";

	ClientPublicViewVO findByClientNumber(String clientNumber);

	Page<ClientPublicViewVO> findAllNonIndividualClients(Integer pageNo, Integer pageSize, String sortBy);

}
