package ca.bc.gov.api.m.oracle.legacyclient.service;

import java.util.List;

import org.springframework.data.domain.Page;

import ca.bc.gov.api.m.oracle.legacyclient.entity.ForestClientEntity;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

public interface LegacyClientService {
	
	String BEAN_NAME = "legacyClientViewService";

	ClientPublicViewVO findByClientNumber(String clientNumber);

	Page<ClientPublicViewVO> findAllNonIndividualClients(Integer pageNo, Integer pageSize, String sortBy);

	List<ForestClientEntity> validateFirstNationBand();

}
