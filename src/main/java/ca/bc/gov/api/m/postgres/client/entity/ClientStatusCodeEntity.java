package ca.bc.gov.api.m.postgres.client.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ca.bc.gov.api.core.entity.AbstractEntity;
import ca.bc.gov.api.core.misc.scope.ScopeConstant;

@Component(ClientStatusCodeEntity.BEAN_NAME)
@Scope(ScopeConstant.PROTOTYPE)
@Entity
@Table(name = "CLIENT_STATUS_CODE", schema = "public")
public class ClientStatusCodeEntity implements AbstractEntity {

	private static final long serialVersionUID = -1L;

	public static final String BEAN_NAME = "clientStatusCodeEntity";

	@Id
	@Column(name = "CLIENT_STATUS_CODE")
	private String clientStatusCode;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "EFFECTIVE_DATE")
	private Date effectiveDate;

	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Column(name = "CREATE_TIMESTAMP")
	private Timestamp createTimestamp;

	@Column(name = "UPDATE_TIMESTAMP")
	private Timestamp updateTimestamp;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "UPDATE_USER")
	private String updateUser;

	public String getClientStatusCode() {
		return clientStatusCode;
	}

	public void setClientStatusCode(String clientStatusCode) {
		this.clientStatusCode = clientStatusCode;
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

	public Timestamp getCreateTimestamp() {
		return createTimestamp;
	}

	public void setCreateTimestamp(Timestamp createTimestamp) {
		this.createTimestamp = createTimestamp;
	}

	public Timestamp getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Timestamp updateTimestamp) {
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

	public void setUpdateUsere(String updateUser) {
		this.updateUser = updateUser;
	}
}
