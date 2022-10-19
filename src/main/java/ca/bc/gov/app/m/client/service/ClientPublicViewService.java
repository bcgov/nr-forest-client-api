package ca.bc.gov.app.m.client.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ca.bc.gov.app.m.client.entity.ClientPublicViewEntity;

public interface ClientPublicViewService {
	
	String BEAN_NAME = "clientPublicViewService";

	List<ClientPublicViewEntity> findByClientNumber(String clientNumber);

	Page<ClientPublicViewEntity> findAllNonIndividualClients(Integer pageNo, Integer pageSize, String sortBy);

}
