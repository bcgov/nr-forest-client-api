package ca.bc.gov.api.m.postgres.client.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import ca.bc.gov.api.core.util.CoreUtil;
import ca.bc.gov.api.m.postgres.client.entity.ClientStatusCodeEntity;
import ca.bc.gov.api.m.postgres.client.repository.ClientStatusCodeRepository;
import ca.bc.gov.api.m.postgres.client.service.ClientService;

@Service(ClientService.BEAN_NAME)
public class ClientServiceImpl implements ClientService {

	@Inject
	private ClientStatusCodeRepository clientStatusCodeRepository;
	
	@Inject
	private CoreUtil coreUtil;

	@Override
	public List<ClientStatusCodeEntity> findAllClientStatusCodes() {
		return clientStatusCodeRepository.findAll();
	}

    @Override
    public List<ClientStatusCodeEntity> findActiveClientStatusCodes() {
        return clientStatusCodeRepository.findActiveAt(coreUtil.getCurrentTime());
    }
	
}
