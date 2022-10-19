package ca.bc.gov.app.m.client.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.app.m.client.service.ClientPublicViewService;

@RestController
@RequestMapping("app/m/clientpublic/")
public class ClientPublicViewController {

	public static final Logger logger = LoggerFactory.getLogger(ClientPublicViewController.class);
	
	@Inject
	private ClientPublicViewService clientPublicViewService;
	
	@RequestMapping(value = "/findByNumber", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public String findByNumber() {
    	return "Hola Mundo!";    	
    }
	
}
