package ca.bc.gov.api.m.oracle.legacyclient.vo;

import java.io.Serializable;

public class ClientPublicViewVO implements Serializable {

	private static final long serialVersionUID = -3558915712555555036L;

	public String clientNumber;
	public String clientName;
	public String legalFirstName;
	public String legalMiddleName;
	public String clientStatusCode;
	public String clientTypeCode;
	
	public ClientPublicViewVO() {
		super();
	}

	public ClientPublicViewVO(String clientNumber, 
							  String clientName, 
							  String legalFirstName, 
							  String legalMiddleName,
							  String clientStatusCode, 
							  String clientTypeCode) {
		super();
		this.clientNumber = clientNumber;
		this.clientName = clientName;
		this.legalFirstName = legalFirstName;
		this.legalMiddleName = legalMiddleName;
		this.clientStatusCode = clientStatusCode;
		this.clientTypeCode = clientTypeCode;
	}
	
}
