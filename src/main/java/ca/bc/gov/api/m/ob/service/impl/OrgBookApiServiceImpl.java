package ca.bc.gov.api.m.ob.service.impl;

import java.net.URI;
import java.net.URISyntaxException;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ca.bc.gov.api.core.util.CoreUtil;
import ca.bc.gov.api.m.ob.service.OrgBookApiService;
import ca.bc.gov.api.m.ob.vo.OrgBookResponseVO;

@Service(OrgBookApiService.BEAN_NAME)
public class OrgBookApiServiceImpl implements OrgBookApiService {
    
    public static final Logger logger = LoggerFactory.getLogger(OrgBookApiServiceImpl.class);

    @Inject
    private CoreUtil coreUtil;
    
    
    @Override
    public ResponseEntity<Object> findByIncorporationNumber(String incorporationNumber) {
        String url = "https://orgbook.gov.bc.ca/api/v4/search/topic?format=json&inactive=any&latest=true&ordering=-score&q=" +
                      incorporationNumber + "&revoked=false";
        RestTemplate restTemplate = new RestTemplate();
        String restCallResponse = restTemplate.getForObject(toURI(url), String.class);
        
        OrgBookResponseVO response = coreUtil.jsonStringToObj(restCallResponse, OrgBookResponseVO.class);
        
        logger.info("Response: " + response.toString());
        logger.info("Results: " + response.results);

        return ResponseEntity.ok(response);
    }

    private URI toURI(String uri) {
        try {
            return new URI(uri);
        }
        catch (URISyntaxException e) {
            throw new IllegalArgumentException("Failed to convert input value to URI. " + e.toString());
        }
    }
    
}
