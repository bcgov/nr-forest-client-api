package ca.bc.gov.api.mpg.clientstatuscode.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.api.mpg.clientstatuscode.repository.ClientStatusCodeRepository;
// import ca.bc.gov.api.mpg.clientstatuscode.vo.ClientStatusCodeVO;
import ca.bc.gov.api.mpg.clientstatuscode.entity.ClientStatusCodeEntity;

@RestController
@RequestMapping("api/mpg/clientstatuscode")
public class ClientStatusCodeController {

	public static final Logger logger = LoggerFactory.getLogger(ClientStatusCodeController.class);

  @Inject
	ClientStatusCodeRepository clientStatusCodeRepository;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
	public List<ClientStatusCodeEntity> findAllClientStatusCode() {
		return clientStatusCodeRepository.findAll();
	}

}
