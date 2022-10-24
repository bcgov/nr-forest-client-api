package ca.bc.gov.api.m.postgres.client.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ca.bc.gov.api.core.configuration.PostgresPersistenceConfiguration;
import ca.bc.gov.api.m.postgres.client.entity.ClientStatusCodeEntity;
import ca.bc.gov.api.m.postgres.client.service.ClientService;
import io.swagger.annotations.Api;

@Api(tags = PostgresPersistenceConfiguration.POSTGRES_API_TAG)
@RestController
@RequestMapping("api/m/client")
public class ClientController {

    public static final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Inject
    private ClientService clientService;

    @RequestMapping(value = "/findAllClientStatusCodes", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<ClientStatusCodeEntity> findAllClientStatusCodes() {
        return clientService.findAllClientStatusCodes();
    }
    
    @RequestMapping(value = "/findActiveClientStatusCodes", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public List<ClientStatusCodeEntity> findActiveClientStatusCodes() {
        return clientService.findActiveClientStatusCodes();
    }

}
