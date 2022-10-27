package ca.bc.gov.api.m.postgres.client.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ca.bc.gov.api.core.configuration.PostgresPersistenceConfiguration;
import ca.bc.gov.api.core.entity.AbstractEntity;
import ca.bc.gov.api.core.misc.scope.ScopeConstant;

@Entity
@Table(name = "CLIENT_TYPE_CODE", schema = PostgresPersistenceConfiguration.POSTGRES_ATTRIBUTE_SCHEMA)
@Component(ClientTypeCodeEntity.BEAN_NAME)
@Scope(ScopeConstant.PROTOTYPE)
public class ClientTypeCodeEntity implements AbstractEntity {

	private static final long serialVersionUID = 8069253248355277428L;

	public static final String BEAN_NAME = "clientTypeCodeEntity";
	
	public static final String INDIVIDUAL	       = "I";
	public static final String ASSOCIATION         = "A";
	public static final String CORPORATION         = "C";
	public static final String FIRST_NATION_BAND   = "B";
	

	@Id
	@Column(name = "CLIENT_TYPE_CODE")
	private String clientTypeCode;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Column(name = "CREATE_TIMESTAMP")
	private Date createTimestamp;

	@Column(name = "UPDATE_TIMESTAMP")
	private Date updateTimestamp;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	public String getClientTypeCode() {
		return clientTypeCode;
	}

	public void setClientTypeCode(String clientTypeCode) {
		this.clientTypeCode = clientTypeCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Date createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

}
