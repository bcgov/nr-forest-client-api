package ca.bc.gov.api.oracle.legacy.entity;

import static ca.bc.gov.api.oracle.legacy.ApplicationConstants.INDIVIDUAL;
import static ca.bc.gov.api.oracle.legacy.ApplicationConstants.ORACLE_ATTRIBUTE_SCHEMA;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@With
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FOREST_CLIENT", schema = ORACLE_ATTRIBUTE_SCHEMA)
public class ForestClientEntity {

  @Id
  @Column("CLIENT_NUMBER")
  private String clientNumber;

  @Column("CLIENT_NAME")
  private String clientName;

  @Column("LEGAL_FIRST_NAME")
  private String legalFirstName;

  @Column("LEGAL_MIDDLE_NAME")
  private String legalMiddleName;

  @Column("CLIENT_STATUS_CODE")
  private String clientStatusCode;

  @Column("CLIENT_TYPE_CODE")
  private String clientTypeCode;

  @Column("CLIENT_ID_TYPE_CODE")
  private String clientIdTypeCode;

  @Column("CLIENT_IDENTIFICATION ")
  private String clientIdentification;

  @Column("REGISTRY_COMPANY_TYPE_CODE")
  private String registryCompanyTypeCode;

  @Column("CORP_REGN_NMBR")
  private String corpRegnNmbr;

  @Column("CLIENT_ACRONYM")
  private String clientAcronym;

  @Column("WCB_FIRM_NUMBER")
  private String wcbFirmNumber;

  @Column("OCG_SUPPLIER_NMBR")
  private String ocgSupplierNmbr;

  @Column("CLIENT_COMMENT")
  private String clientComment;

  @Transient
  public String getName() {
    if (Objects.equals(this.clientTypeCode, INDIVIDUAL)) {
      return Stream.of(this.legalFirstName, this.legalMiddleName, this.clientName)
              .filter(Objects::nonNull)
              .collect(Collectors.joining(" "));
    } else {
      return this.clientName;
    }
  }

}
