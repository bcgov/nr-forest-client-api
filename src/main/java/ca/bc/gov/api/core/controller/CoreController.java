package ca.bc.gov.api.core.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreController {

	@GetMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
	public String api() {
		return "Hello World!";
	}
	
}
