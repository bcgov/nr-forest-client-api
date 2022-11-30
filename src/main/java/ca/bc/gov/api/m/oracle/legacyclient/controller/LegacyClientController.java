package ca.bc.gov.api.m.oracle.legacyclient.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.api.m.oracle.legacyclient.service.LegacyClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;

@Api(tags="Legacy Client")
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("api/m/legacyclient/")
public class LegacyClientController {

    public static final Logger logger = LoggerFactory.getLogger(LegacyClientController.class);

    @Inject
    private LegacyClientService legacyClientService;


    @RequestMapping(value = "/findByClientNumber", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByClientNumber(@RequestParam("clientNumber") String clientNumber) {
    	return legacyClientService.findByClientNumber(clientNumber);
    }

    @GetMapping("/findAllNonIndividuals")
    public ResponseEntity<Object> findAllNonIndividuals(@RequestParam(defaultValue = "1") String currentPage,
                                                        @RequestParam(defaultValue = "10") String itemsPerPage,
                                                        @RequestParam(defaultValue = "clientName") String sortedColumnName) {

    	return legacyClientService.findAllNonIndividualClients(currentPage, itemsPerPage, sortedColumnName);
    }
    
    @RequestMapping(value = "/findByNames", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByNames(@RequestParam(name="clientName", required=false) 
    										  @ApiParam(value = "The name of the entity or individual's last name") 
    										  String clientName,
    										  
    										  @RequestParam(name="clientFirstName", required=false) 
    										  @ApiParam(value = "The client's first name") 
    										  String clientFirstName,
    										  
    										  @RequestParam(name="clientMiddleName", required=false) 
    										  @ApiParam(value = "The client's middle name") 
    										  String clientMiddleName,
    										  
    										  @RequestParam(name="clientTypeCodesAsCsv", required=false) 
    										  @ApiParam(value = "A code indicating a type of ministry client.<br>" +
    									                 		"Examples include but are not limited to: Corporation, Individual, Association, First Nation Band...<br>" + 
    									                 		"Please enter one or more client type codes as CSV, i.e. C,A,B.") 
    										  String clientTypeCodesAsCsv,
    										  
    										  @RequestParam(defaultValue = "1") String currentPage,
                                              @RequestParam(defaultValue = "10") String itemsPerPage) {
    	
    	return legacyClientService.findByNames(clientName,
											   clientFirstName,
											   clientMiddleName,
											   clientTypeCodesAsCsv,
											   currentPage,
											   itemsPerPage);
    }

}
