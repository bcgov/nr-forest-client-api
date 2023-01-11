package ca.bc.gov.api.m.oracle.legacyclient.service.impl;

import ca.bc.gov.api.core.util.CoreUtil;
import ca.bc.gov.api.m.oracle.legacyclient.dao.ClientPublicViewDao;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientTypeCodeEntity;
import ca.bc.gov.api.m.oracle.legacyclient.repository.ClientPublicViewRepository;
import ca.bc.gov.api.m.oracle.legacyclient.service.LegacyClientService;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicFilterObjectVO;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicFilterObjectVO.ClientTypeHelper;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;
import javax.inject.Inject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
				return ResponseEntity.ok(null);
			}
        } 
        else {
            return new ResponseEntity<Object>("Couldn't recognize one or many properties. Please check parameters!",
                                              HttpStatus.BAD_REQUEST);
        }
	}

	@Override
	public ResponseEntity<Object> findAllNonIndividualClients(String currentPageAsString, 
															  String itemsPerPageAsString, 
															  String sortedColumnName) {
		
		if (!coreUtil.isNumber(currentPageAsString) || !coreUtil.isNumber(itemsPerPageAsString)) {
			return new ResponseEntity<Object>("Please make sure the currentPage and itemsPerPage are positive numbers",
                    						  HttpStatus.BAD_REQUEST);
		}
		else {
			int currentPage = Integer.valueOf(currentPageAsString);
			int itemsPerPage = Integer.valueOf(itemsPerPageAsString);
			
			if (currentPage <= 0 || itemsPerPage <= 0) {
	            return new ResponseEntity<Object>("Please make sure the currentPage and itemsPerPage are positive numbers",
	                                              HttpStatus.BAD_REQUEST);
	        }
			else {
				ClientPublicFilterObjectVO filterObject = new ClientPublicFilterObjectVO();
				filterObject.currentPage = currentPage;
				filterObject.itemsPerPage = itemsPerPage;
				filterObject.sortedColumnName = sortedColumnName;
				filterObject.clientType = new ClientTypeHelper();
				filterObject.clientType.clientTypeCode = ClientTypeCodeEntity.INDIVIDUAL;
				filterObject.clientType.equalsInd = false;
				
				return ResponseEntity.ok(clientPublicViewDao.retrieveSearchResultItems(filterObject));
			}
		}
	}

	@Override
	public ResponseEntity<Object> findByNames(String clientName, 
											  String clientFirstName, 
											  String clientMiddleName,
											  String clientTypeCodesAsCsv, 
											  String currentPageAsString, 
											  String itemsPerPageAsString) {
		
		if (coreUtil.isNullOrBlank(clientName) && 
			coreUtil.isNullOrBlank(clientFirstName) &&
			coreUtil.isNullOrBlank(clientMiddleName) && 
			coreUtil.isNullOrBlank(clientTypeCodesAsCsv)) {
				
			return new ResponseEntity<Object>("Please add at least one parameter.",
	                   						  HttpStatus.BAD_REQUEST);
		}
		else {
			if (!coreUtil.isNumber(currentPageAsString) || !coreUtil.isNumber(itemsPerPageAsString)) {
				return new ResponseEntity<Object>("Please make sure the currentPage and itemsPerPage are positive numbers",
	                    						  HttpStatus.BAD_REQUEST);
			}
			else {
				int currentPage = Integer.valueOf(currentPageAsString);
				int itemsPerPage = Integer.valueOf(itemsPerPageAsString);
				
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
	
}
