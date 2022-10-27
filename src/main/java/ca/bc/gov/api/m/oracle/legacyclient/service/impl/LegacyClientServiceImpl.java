package ca.bc.gov.api.m.oracle.legacyclient.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ForestClientEntity;
import ca.bc.gov.api.m.oracle.legacyclient.repository.ClientPublicViewRepository;
import ca.bc.gov.api.m.oracle.legacyclient.repository.ForestClientRepository;
import ca.bc.gov.api.m.oracle.legacyclient.service.LegacyClientService;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

@Service(LegacyClientService.BEAN_NAME)
public class LegacyClientServiceImpl implements LegacyClientService {
		
	@Inject
	private ClientPublicViewRepository clientPublicViewRepository;

	@Inject
    private ForestClientRepository forestClientRepository;
	
	
	@Override
	public ClientPublicViewVO findByClientNumber(String clientNumber) {
		ClientPublicViewEntity client = clientPublicViewRepository.findByClientNumber(clientNumber);
		
		return new ClientPublicViewVO(
    		        client.getClientNumber(),
    		        client.getClientName(),
    		        client.getLegalFirstName(),
                    client.getLegalMiddleName(),
                    client.getClientStatusCode(),
                    client.getClientTypeCode());
	}

	@Override
	public Page<ClientPublicViewVO> findAllNonIndividualClients(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		Page<ClientPublicViewEntity> clients = clientPublicViewRepository.findAllNonIndividualClients(paging);
		return toClientPublicViewVOs(clients, paging);
	}

	private Page<ClientPublicViewVO> toClientPublicViewVOs(Page<ClientPublicViewEntity> clients, Pageable paging) {
		if (null != clients && clients.getSize() > 0) {
			return new PageImpl<>(clients.stream()
					  .map(e -> new ClientPublicViewVO(
							  e.getClientNumber(),
							  e.getClientName(),
							  e.getLegalFirstName(),
							  e.getLegalMiddleName(),
							  e.getClientStatusCode(),
							  e.getClientTypeCode()))
					  .collect(Collectors.toList()), paging, clients.getTotalElements());
		}
		else {
			return null;
		}
	}

	@Override
	public List<ForestClientEntity> validateFirstNationBand() {
		List<ForestClientEntity> clients = forestClientRepository.findAllFirstNationBandClients();
		
		// todo: go for each client to make the api call
		// for (ClientPublicViewEntity client : clients) { 
		// 		System.out.println(client.getClientName());
		// }

		return clients;

	}

}
