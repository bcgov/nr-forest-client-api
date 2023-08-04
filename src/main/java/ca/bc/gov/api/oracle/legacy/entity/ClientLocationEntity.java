package ca.bc.gov.api.oracle.legacy.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
@Table(name = "CLIENT_LOCATION", schema = "THE")
public class ClientLocationEntity {

  @Column("CLIENT_NUMBER")
  private String clientNumber;
  @Column("CLIENT_LOCN_CODE")
  private String locationCode;

  @Column("CLIENT_LOCN_NAME")
  private String locationName;

  @Column("HDBS_COMPANY_CODE")
  private String companyCode;

  @Column("ADDRESS_1")
  private String address1;

  @Column("ADDRESS_2")
  private String address2;

  @Column("ADDRESS_3")
  private String address3;

  @Column("CITY")
  private String city;

  @Column("PROVINCE")
  private String province;

  @Column("POSTAL_CODE")
  private String postalCode;

  @Column("COUNTRY")
  private String country;

  @Column("BUSINESS_PHONE")
  private String businessPhone;

  @Column("HOME_PHONE")
  private String homePhone;

  @Column("CELL_PHONE")
  private String cellPhone;

  @Column("FAX_NUMBER")
  private String faxNumber;

  @Column("EMAIL_ADDRESS")
  private String email;

  @Column("LOCN_EXPIRED_IND")
  private String expired;

  @Column("RETURNED_MAIL_DATE")
  LocalDateTime returnedMailDate;

  @Column("TRUST_LOCATION_IND")
  private String trusted;

  @Column("CLI_LOCN_COMMENT")
  private String comment;
}
