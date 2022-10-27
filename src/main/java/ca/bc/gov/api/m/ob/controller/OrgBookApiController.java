package ca.bc.gov.api.m.ob.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.api.m.ob.service.OrgBookApiService;
import io.swagger.annotations.Api;

//NOTE: 
//This class is for testing purposes. This will be removed. 

@Api(tags = "OrgBook")
@RestController
@RequestMapping("api/m/orgbook/")
public class OrgBookApiController {

    public static final Logger logger = LoggerFactory.getLogger(OrgBookApiController.class);
    
    @Inject
    private OrgBookApiService orgBookApiService;

    @RequestMapping(value = "/findByIncorporationNumber", 
                    method = RequestMethod.GET, 
                    produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> findByIncorporationNumber(@RequestParam("incorporationNumber") String incorporationNumber) {
        return orgBookApiService.findByIncorporationNumber(incorporationNumber);
    }

}
