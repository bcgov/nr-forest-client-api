package ca.bc.gov.api.m.oracle.legacyclient.service;

import org.springframework.http.ResponseEntity;

public interface LegacyClientService {
	
	String BEAN_NAME = "legacyClientViewService";

	ResponseEntity<Object> findByClientNumber(String clientNumber);

	ResponseEntity<Object> findAllNonIndividualClients(String currentPage, 
													   String itemsPerPage,
													   String sortedColumnName);

	ResponseEntity<Object> findByNames(String clientName, 
									   String clientFirstName, 
									   String clientMiddleName,
									   String clientTypeCodesAsCsv, 
									   String currentPage, 
									   String itemsPerPage);

}
