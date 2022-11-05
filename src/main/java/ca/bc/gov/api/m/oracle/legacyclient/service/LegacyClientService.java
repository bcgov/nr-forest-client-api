package ca.bc.gov.api.m.oracle.legacyclient.service;

import org.springframework.http.ResponseEntity;

public interface LegacyClientService {
	
	String BEAN_NAME = "legacyClientViewService";

	ResponseEntity<Object> findByClientNumber(String clientNumber);

	ResponseEntity<Object> findAllNonIndividualClients(Integer currentPage, 
														 Integer itemsPerPage, 
														 String sortBy);

	ResponseEntity<Object> findByNames(String clientName, 
									   String clientFirstName, 
									   String clientMiddleName,
									   String clientTypeCodesAsCsv, 
									   Integer currentPage, 
									   Integer itemsPerPage);

}
