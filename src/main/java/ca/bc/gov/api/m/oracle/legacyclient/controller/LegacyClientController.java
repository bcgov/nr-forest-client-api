package ca.bc.gov.api.m.oracle.legacyclient.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.api.core.configuration.OraclePersistenceConfiguration;
import ca.bc.gov.api.m.oracle.legacyclient.service.LegacyClientService;
import ca.bc.gov.api.m.oracle.legacyclient.vo.ClientPublicViewVO;
import io.swagger.annotations.Api;

@Api(tags = OraclePersistenceConfiguration.ORACLE_API_TAG)
@RestController
@RequestMapping("api/m/legacyclient/")
public class LegacyClientController {

	public static final Logger logger = LoggerFactory.getLogger(LegacyClientController.class);

	@Inject
	private LegacyClientService legacyClientService;

	@RequestMapping(value = "/findByClientNumber", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity<ClientPublicViewVO> findByClientNumber(@RequestParam("clientNumber") String clientNumber) {
		try {
			if (clientNumber.matches("[0-9]+")) {
				return new ResponseEntity<ClientPublicViewVO>(legacyClientService.findByClientNumber(clientNumber), HttpStatus.OK);
			} 
			// todo: how to show error message when client number has letters
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}

	//TODO: Improve logic
	@GetMapping("/findAllNonIndividuals")
	public ResponseEntity<Page<ClientPublicViewVO>> findAllNonIndividuals(@RequestParam(defaultValue = "0") Integer pageNo,
																	      @RequestParam(defaultValue = "10") Integer pageSize,
																		  @RequestParam(defaultValue = "CLIENT_NUMBER") String sortBy) {
		try {
			Page<ClientPublicViewVO> clientData = legacyClientService.findAllNonIndividualClients(pageNo, pageSize, sortBy);
			return new ResponseEntity<Page<ClientPublicViewVO>>(clientData, HttpStatus.OK);
		} 
		catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}