package ca.bc.gov.api.m.oracle.legacyclient.entity;

import ca.bc.gov.api.core.configuration.OraclePersistenceConfiguration;
import ca.bc.gov.api.core.entity.AbstractEntity;
import ca.bc.gov.api.core.misc.scope.ScopeConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "V_CLIENT_PUBLIC", schema = OraclePersistenceConfiguration.ORACLE_ATTRIBUTE_SCHEMA)
@Component(ClientPublicViewEntity.BEAN_NAME)
@Scope(ScopeConstant.PROTOTYPE)
public class ClientPublicViewEntity implements AbstractEntity {

	private static final long serialVersionUID = -3060423136744818530L;

	public static final String BEAN_NAME = "clientPublicViewEntity";

	@Id
	@Column(name = "CLIENT_NUMBER")
	private String clientNumber;

	@Column(name = "CLIENT_NAME")
	private String clientName;

	@Column(name = "LEGAL_FIRST_NAME")
	private String legalFirstName;

	@Column(name = "LEGAL_MIDDLE_NAME")
	private String legalMiddleName;

	@Column(name = "CLIENT_STATUS_CODE")
	private String clientStatusCode;

	@Column(name = "CLIENT_TYPE_CODE")
	private String clientTypeCode;

	public String getClientNumber() {
		return clientNumber;
	}

	public void setClientNumber(String clientNumber) {
		this.clientNumber = clientNumber;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getLegalFirstName() {
		return legalFirstName;
	}

	public void setLegalFirstName(String legalFirstName) {
		this.legalFirstName = legalFirstName;
	}

	public String getLegalMiddleName() {
		return legalMiddleName;
	}

	public void setLegalMiddleName(String legalMiddleName) {
		this.legalMiddleName = legalMiddleName;
	}

	public String getClientStatusCode() {
		return clientStatusCode;
	}

	public void setClientStatusCode(String clientStatusCode) {
		this.clientStatusCode = clientStatusCode;
	}

	public String getClientTypeCode() {
		return clientTypeCode;
	}

	public void setClientTypeCode(String clientTypeCode) {
		this.clientTypeCode = clientTypeCode;
	}

	@Override
	public int hashCode() {
		return Objects.hash(clientNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientPublicViewEntity other = (ClientPublicViewEntity) obj;
		return Objects.equals(clientNumber, other.clientNumber);
	}

}
