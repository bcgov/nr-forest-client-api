package ca.bc.gov.api.m.client.service.impl;

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

import ca.bc.gov.api.m.client.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.client.repository.ClientPublicViewRepository;
import ca.bc.gov.api.m.client.service.ClientPublicViewService;
import ca.bc.gov.api.m.client.vo.ClientPublicViewVO;

@Service(ClientPublicViewService.BEAN_NAME)
public class ClientPublicViewServiceImpl implements ClientPublicViewService {
		
	@Inject
	private ClientPublicViewRepository clientPublicViewRepository;

	@Override
	public List<ClientPublicViewVO> findByClientNumber(String clientNumber) {
		List<ClientPublicViewEntity> clients = clientPublicViewRepository.findByClientNumber(clientNumber);
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
		Page<ClientPublicViewEntity> clients = clientPublicViewRepository.findByClientTypeCodeNotI(paging);
		return toClientPublicViewVOs(clients);
	}

	private Page<ClientPublicViewVO> toClientPublicViewVOs(Page<ClientPublicViewEntity> clients) {
		if (null != clients && clients.getSize() > 0) {
			return new PageImpl<>(clients.stream()
					  .map(e -> new ClientPublicViewVO(
							  e.getClientNumber(),
							  e.getClientName(),
							  e.getLegalFirstName(),
							  e.getLegalMiddleName(),
							  e.getClientStatusCode(),
							  e.getClientTypeCode()))
					  .collect(Collectors.toList()));
		}
		else {
			return null;
		}
	}

}
