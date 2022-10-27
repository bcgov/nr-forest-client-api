package ca.bc.gov.api.m.oracle.legacyclient.entity;

import java.util.Objects;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ca.bc.gov.api.core.configuration.OraclePersistenceConfiguration;
import ca.bc.gov.api.core.entity.AbstractEntity;
import ca.bc.gov.api.core.misc.scope.ScopeConstant;

@Entity
@Table(name = "FOREST_CLIENT", schema = OraclePersistenceConfiguration.ORACLE_ATTRIBUTE_SCHEMA)
@Component(ForestClientEntity.BEAN_NAME)
@Scope(ScopeConstant.PROTOTYPE)
public class ForestClientEntity implements AbstractEntity {

	private static final long serialVersionUID = 832879803987592344L;

	public static final String BEAN_NAME = "forestClientEntity";

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

    @Column(name = "BIRTHDATE")
    private Date birthdate;

    @Column(name = "CLIENT_ID_TYPE_CODE")
    private String clientIdTypeCode;

    @Column(name = "CLIENT_IDENTIFICATION ")
    private String clientIdentification;

    @Column(name = "REGISTRY_COMPANY_TYPE_CODE")
    private String registryCompanyTypeCode;

    @Column(name = "CORP_REGN_NMBR")
    private String corpRegnNmbr;

    @Column(name = "CLIENT_ACRONYM")
    private String clientAcronym;

    @Column(name = "WCB_FIRM_NUMBER")
    private String wcbFirmNumber;

    @Column(name = "OCG_SUPPLIER_NMBR")
    private String ocgSupplierNmbr;

    @Column(name = "CLIENT_COMMENT")
    private String clientComment;

    @Column(name = "ADD_TIMESTAMP")
    private Date addTimestamp;

    @Column(name = "ADD_USERID")
    private String addUserId;

    @Column(name = "ADD_ORG_UNIT")
    private Long addOrgUnit;

    @Column(name = "UPDATE_TIMESTAMP")
    private Date updateTimestamp;

    @Column(name = "UPDATE_USERID")
    private String updateUserId;

    @Column(name = "UPDATE_ORG_UNIT")
    private Long updateOrgUnit;

    @Column(name = "REVISION_COUNT")
    private Long revisionCount;

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getClientIdTypeCode() {
        return clientIdTypeCode;
    }

    public void setClientIdTypeCode(String clientIdTypeCode) {
        this.clientIdTypeCode = clientIdTypeCode;
    }

    public String getClientIdentification() {
        return clientIdentification;
    }

    public void setClientIdentification(String clientIdentification) {
        this.clientIdentification = clientIdentification;
    }

    public String getRegistryCompanyTypeCode() {
        return registryCompanyTypeCode;
    }

    public void setRegistryCompanyTypeCode(String registryCompanyTypeCode) {
        this.registryCompanyTypeCode = registryCompanyTypeCode;
    }

    public String getCorpRegnNmbr() {
        return corpRegnNmbr;
    }

    public void setCorpRegnNmbr(String corpRegnNmbr) {
        this.corpRegnNmbr = corpRegnNmbr;
    }

    public String getClientAcronym() {
        return clientAcronym;
    }

    public void setClientAcronym(String clientAcronym) {
        this.clientAcronym = clientAcronym;
    }

    public String getWcbFirmNumber() {
        return wcbFirmNumber;
    }

    public void setWcbFirmNumber(String wcbFirmNumber) {
        this.wcbFirmNumber = wcbFirmNumber;
    }

    public String getOcgSupplierNmbr() {
        return ocgSupplierNmbr;
    }

    public void setOcgSupplierNmbr(String ocgSupplierNmbr) {
        this.ocgSupplierNmbr = ocgSupplierNmbr;
    }

    public String getClientComment() {
        return clientComment;
    }

    public void setClientComment(String clientComment) {
        this.clientComment = clientComment;
    }

    public Date getAddTimestamp() {
        return addTimestamp;
    }

    public void setAddTimestamp(Date addTimestamp) {
        this.addTimestamp = addTimestamp;
    }

    public String getAddUserId() {
        return addUserId;
    }

    public void setAddUserId(String addUserId) {
        this.addUserId = addUserId;
    }

    public Number getAddOrgUnit() {
        return addOrgUnit;
    }

    public void setAddOrgUnit(Long addOrgUnit) {
        this.addOrgUnit = addOrgUnit;
    }

    public Date getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Date updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Number getUpdateOrgUnit() {
        return updateOrgUnit;
    }

    public void setUpdateOrgUnit(Long updateOrgUnit) {
        this.updateOrgUnit = updateOrgUnit;
    }

    public Number getRevisionCount() {
        return revisionCount;
    }

    public void setRevisionCount(Long revisionCount) {
        this.revisionCount = revisionCount;
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
        ForestClientEntity other = (ForestClientEntity) obj;
        return Objects.equals(clientNumber, other.clientNumber);
    }

}
