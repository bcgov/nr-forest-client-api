package ca.bc.gov.api.m.postgres.client.vo;

import java.util.Date;
import java.sql.Timestamp;

import java.io.Serializable;


public class ClientStatusCodeVO implements Serializable {

	private static final long serialVersionUID = -1L;

	public String clientStatusCode;
	public String description;
	public Date effectiveDate;
	public Date expiryDate;
  public Timestamp createTimestamp;
	public Timestamp updateTimestamp;
	public String createUser;
  public String updateUser;
	
	public ClientStatusCodeVO() {
		super();
	}

	public ClientStatusCodeVO(
    String clientStatusCode,
    String description,
    Date effectiveDate,
    Date expiryDate,
    Timestamp createTimestamp,
    Timestamp updateTimestamp,
    String createUser,
    String updateUser
  ) {
		super();
		this.clientStatusCode = clientStatusCode;
		this.description = description;
		this.effectiveDate = effectiveDate;
		this.expiryDate = expiryDate;
		this.createTimestamp = createTimestamp;
		this.updateTimestamp = updateTimestamp;
    this.createUser = createUser;
    this.updateUser = updateUser;
	}
	
}
