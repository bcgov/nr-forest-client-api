package ca.bc.gov.api.m.ob.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrgBookResponseVO {

    @JsonProperty("results")
    public List<Object> results;
    
    //TODO: Add more fields
    
}
