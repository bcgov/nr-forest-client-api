package ca.bc.gov.api.m.oracle.legacyclient.entity;

import java.util.Date;
import java.util.Objects;

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
@Table(name = "CLIENT_LOCATION", schema = OraclePersistenceConfiguration.ORACLE_ATTRIBUTE_SCHEMA)
@Component(ClientLocationEntity.BEAN_NAME)
@Scope(ScopeConstant.PROTOTYPE)
public class ClientLocationEntity implements AbstractEntity {

	private static final long serialVersionUID = -1795480401567670619L;

	public static final String BEAN_NAME = "clientLocationEntity";

    @Id
    @Column(name = "CLIENT_NUMBER")
    private String clientNumber;

    @Column(name = "CLIENT_LOCN_CODE")
    private String clientLocnCode;

    @Column(name = "CLIENT_LOCN_NAME")
    private String clientLocnName;

    @Column(name = "HDBS_COMPANY_CODE")
    private String hdbsCompanyCode;

    @Column(name = "ADDRESS_1")
    private String addressOne;

    @Column(name = "ADDRESS_2")
    private String addressTwo;

    @Column(name = "ADDRESS_3")
    private String addressThree;

    @Column(name = "CITY")
    private String city;

    @Column(name = "PROVINCE")
    private String province;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "BUSINESS_PHONE")
    private String businessPhone;

    @Column(name = "HOME_PHONE")
    private String homePhone;

    @Column(name = "CELL_PHONE")
    private String cellPhone;

    @Column(name = "FAX_NUMBER")
    private String faxNumber;

    @Column(name = "EMAIL_ADDRESS")
    private String emailAddress;

    @Column(name = "LOCN_EXPIRED_IND ")
    private String locnExpiredInd;

    @Column(name = "RETURNED_MAIL_DATE")
    private Date returnedMailDate;

    @Column(name = "TRUST_LOCATION_IND")
    private String trustLocationInd;

    @Column(name = "CLI_LOCN_COMMENT ")
    private String cliLocnComment;

    @Column(name = "UPDATE_TIMESTAMP")
    private Date updateTimestamp;

    @Column(name = "UPDATE_USERID")
    private String updateUserId;

    @Column(name = "UPDATE_ORG_UNIT")
    private Long updateOrgUnit;

    @Column(name = "ADD_TIMESTAMP")
    private Date addTimestamp;

    @Column(name = "ADD_USERID")
    private String addUserId;

    @Column(name = "ADD_ORG_UNIT")
    private Long addOrgUnit;

    @Column(name = "REVISION_COUNT")
    private Long revisionCount;

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getClientLocnCode() {
        return clientLocnCode;
    }

    public void setClientLocnCode(String clientLocnCode) {
        this.clientLocnCode = clientLocnCode;
    }

    public String getClientLocnName() {
        return clientLocnName;
    }

    public void setClientLocnName(String clientLocnName) {
        this.clientLocnName = clientLocnName;
    }

    public String getHdbsCompanyCode() {
        return hdbsCompanyCode;
    }

    public void setHdbsCompanyCode(String hdbsCompanyCode) {
        this.hdbsCompanyCode = hdbsCompanyCode;
    }

    public String getAddressOne() {
        return addressOne;
    }

    public void setAddressOne(String addressOne) {
        this.addressOne = addressOne;
    }

    public String getAddressTwo() {
        return addressTwo;
    }

    public void setAddressTwo(String addressTwo) {
        this.addressTwo = addressTwo;
    }

    public String getAddressThree() {
        return addressThree;
    }

    public void setAddressThree(String addressThree) {
        this.addressThree = addressThree;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBusinessPhone() {
        return businessPhone;
    }

    public void setBusinessPhone(String businessPhone) {
        this.businessPhone = businessPhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLocnExpiredInd() {
        return locnExpiredInd;
    }

    public void setLocnExpiredInd(String locnExpiredInd) {
        this.locnExpiredInd = locnExpiredInd;
    }

    public Date getReturnedMailDate() {
        return returnedMailDate;
    }

    public void setReturnedMailDate(Date returnedMailDate) {
        this.returnedMailDate = returnedMailDate;
    }

    public String getTrustLocationInd() {
        return trustLocationInd;
    }

    public void setTrustLocationInd(String trustLocationInd) {
        this.trustLocationInd = trustLocationInd;
    }

    public String getCliLocnComment() {
        return cliLocnComment;
    }

    public void setCliLocnComment(String cliLocnComment) {
        this.cliLocnComment = cliLocnComment;
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

    public Long getUpdateOrgUnit() {
        return updateOrgUnit;
    }

    public void setUpdateOrgUnit(Long updateOrgUnit) {
        this.updateOrgUnit = updateOrgUnit;
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

    public Long getAddOrgUnit() {
        return addOrgUnit;
    }

    public void setAddOrgUnit(Long addOrgUnit) {
        this.addOrgUnit = addOrgUnit;
    }

    public Long getRevisionCount() {
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
        ClientLocationEntity other = (ClientLocationEntity) obj;
        return Objects.equals(clientNumber, other.clientNumber);
    }

}
