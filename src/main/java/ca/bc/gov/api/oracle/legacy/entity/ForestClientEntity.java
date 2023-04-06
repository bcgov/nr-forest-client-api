package ca.bc.gov.api.oracle.legacy.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

<<<<<<<< HEAD:src/main/java/ca/bc/gov/api/oracle/legacy/entity/ClientPublicViewEntity.java
@Data
@With
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Table(name = "V_CLIENT_PUBLIC", schema = "THE")
public class ClientPublicViewEntity extends ForestClientBaseEntity {
  public static final String INDIVIDUAL = "I";
========
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Table(name = "FOREST_CLIENT", schema = "THE")
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

>>>>>>>> 4d191c8 (fix: changing acronym to singular):src/main/java/ca/bc/gov/api/oracle/legacy/entity/ForestClientEntity.java
}
