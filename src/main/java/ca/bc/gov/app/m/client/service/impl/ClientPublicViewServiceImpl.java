package ca.bc.gov.app.m.client.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ca.bc.gov.app.m.client.entity.ClientPublicViewEntity;
import ca.bc.gov.app.m.client.repository.ClientPublicViewRepository;
import ca.bc.gov.app.m.client.service.ClientPublicViewService;

@Service(ClientPublicViewService.BEAN_NAME)
public class ClientPublicViewServiceImpl implements ClientPublicViewService {
	
	//TODO: Use VO instead of entity 
	
	@Inject
	private ClientPublicViewRepository clientPublicViewRepository;

	@Override
	public List<ClientPublicViewEntity> findByClientNumber(String clientNumber) {
		return clientPublicViewRepository.findByClientNumber(clientNumber);
	}

	@Override
	public Page<ClientPublicViewEntity> findAllNonIndividualClients(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		Page<ClientPublicViewEntity> pagedResult = clientPublicViewRepository.findByClientTypeCodeNotI(paging);

		return pagedResult;
	}

}
