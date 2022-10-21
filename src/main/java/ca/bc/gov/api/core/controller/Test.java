package ca.bc.gov.api.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Test {
	
	@RequestMapping(value = "/home")
	public String home() {
		return "index";
	}
	
}
