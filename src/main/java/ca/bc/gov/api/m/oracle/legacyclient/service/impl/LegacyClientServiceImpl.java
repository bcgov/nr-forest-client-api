package ca.bc.gov.api.m.oracle.legacyclient.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.repository.LegacyClientRepository;
import ca.bc.gov.api.m.oracle.legacyclient.service.LegacyClientService;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

@Service(LegacyClientService.BEAN_NAME)
public class LegacyClientServiceImpl implements LegacyClientService {
		
	@Inject
	private LegacyClientRepository legacyClientRepository;

	@Override
	public List<ClientPublicViewVO> findByClientNumber(String clientNumber) {
		List<ClientPublicViewEntity> clients = legacyClientRepository.findByClientNumber(clientNumber);
		return toClientPublicViewVOs(clients);
	}

	private List<ClientPublicViewVO> toClientPublicViewVOs(List<ClientPublicViewEntity> clients) {
		if (CollectionUtils.isNotEmpty(clients)) {
			return clients.stream()
						  .map(e -> new ClientPublicViewVO(
										  e.getClientNumber(),
										  e.getClientName(),
										  e.getLegalFirstName(),
										  e.getLegalMiddleName(),
										  e.getClientStatusCode(),
										  e.getClientTypeCode()))
						  .collect(Collectors.toList());
		}
		else {
			return null;
		}
	}

	@Override
	public Page<ClientPublicViewVO> findAllNonIndividualClients(Integer pageNo, Integer pageSize, String sortBy) {
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).ascending());
		Page<ClientPublicViewEntity> clients = legacyClientRepository.findAllNonIndividualClients(paging);
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

}
