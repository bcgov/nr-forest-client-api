package ca.bc.gov.api.m.client.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ca.bc.gov.api.m.client.vo.ClientPublicViewVO;

public interface ClientPublicViewService {
	
	String BEAN_NAME = "clientPublicViewService";

	List<ClientPublicViewVO> findByClientNumber(String clientNumber);

	Page<ClientPublicViewVO> findAllNonIndividualClients(Integer pageNo, Integer pageSize, String sortBy);

}