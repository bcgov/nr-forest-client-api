package ca.bc.gov.api.m.oracle.legacyclient.service.impl;

import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ca.bc.gov.api.core.util.CoreUtil;
import ca.bc.gov.api.m.oracle.legacyclient.dao.ClientPublicViewDao;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.repository.ClientPublicViewRepository;
import ca.bc.gov.api.m.oracle.legacyclient.service.LegacyClientService;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicFilterObjectVO;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;

@Service(LegacyClientService.BEAN_NAME)
public class LegacyClientServiceImpl implements LegacyClientService {
		
	@Inject
	private ClientPublicViewRepository clientPublicViewRepository;	
	
	@Inject
	private ClientPublicViewDao clientPublicViewDao;
	
	@Inject
    private CoreUtil coreUtil;
	
	@Override
	public ResponseEntity<Object> findByClientNumber(String clientNumber) {
		if (coreUtil.isNumber(clientNumber)) {
			ClientPublicViewEntity client = clientPublicViewRepository.findByClientNumber(clientNumber);
			
			if (null != client) {
				return ResponseEntity.ok(new ClientPublicViewVO(
							    		        client.getClientNumber(),
							    		        client.getClientName(),
							    		        client.getLegalFirstName(),
							                    client.getLegalMiddleName(),
							                    client.getClientStatusCode(),
							                    client.getClientTypeCode()));
			}
			else {
				return null;
			}
        } 
        else {
            return new ResponseEntity<Object>("Couldn't recognize one or many properties. Please check parameters!",
                                              HttpStatus.BAD_REQUEST);
        }
	}

	@Override
	public ResponseEntity<Object> findAllNonIndividualClients(Integer currentPage, Integer itemsPerPage, String sortBy) {
		if (itemsPerPage <= 0) {
			return new ResponseEntity<Object>("Please make sure the itemsPerPage is a positive number",
					  						  HttpStatus.BAD_REQUEST);
		}
		else {
			Pageable paging = PageRequest.of(currentPage, itemsPerPage, Sort.by(sortBy).ascending());
			Page<ClientPublicViewEntity> clients = clientPublicViewRepository.findAllNonIndividualClients(paging);
			return ResponseEntity.ok(toClientPublicViewVOs(clients, paging));
		}
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
	public ResponseEntity<Object> findByNames(String clientName, 
											  String clientFirstName, 
											  String clientMiddleName,
											  String clientTypeCodesAsCsv, 
											  Integer currentPage, 
											  Integer itemsPerPage) {
		
		if (coreUtil.isNullOrBlank(clientName) && 
			coreUtil.isNullOrBlank(clientFirstName) &&
			coreUtil.isNullOrBlank(clientMiddleName) && 
			coreUtil.isNullOrBlank(clientTypeCodesAsCsv)) {
				
			return new ResponseEntity<Object>("Please add at least one parameter.",
	                   						  HttpStatus.BAD_REQUEST);
		}
		else {
            if (currentPage <= 0 || itemsPerPage <= 0) {
                return new ResponseEntity<Object>("Please make sure the currentPage and itemsPerPage are positive numbers",
                                                  HttpStatus.BAD_REQUEST);
            }
			else {
				ClientPublicFilterObjectVO filterObject = new ClientPublicFilterObjectVO();
				filterObject.clientName = clientName;
				filterObject.clientFirstName = clientFirstName;
				filterObject.clientMiddleName = clientMiddleName;
				filterObject.clientTypeCodesAsCsv = clientTypeCodesAsCsv;
				filterObject.currentPage = currentPage;
				filterObject.itemsPerPage = itemsPerPage;
					
				return ResponseEntity.ok(clientPublicViewDao.retrieveSearchResultItems(filterObject));
			}
		}
	}
}
