package ca.bc.gov.api.oracle.legacy.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@With
@Table(name = "FOREST_CLIENT", schema = "THE")
public class ForestClientEntity extends ForestClientBaseEntity {

  @Column("BIRTHDATE")
  private LocalDate birthdate;

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

  @Column("ADD_TIMESTAMP")
  private LocalDateTime addTimestamp;

  @Column("ADD_USERID")
  private String addUserId;

  @Column("ADD_ORG_UNIT")
  private Long addOrgUnit;

  @Column("UPDATE_TIMESTAMP")
  private LocalDateTime updateTimestamp;

  @Column("UPDATE_USERID")
  private String updateUserId;

  @Column("UPDATE_ORG_UNIT")
  private Long updateOrgUnit;

  @Column("REVISION_COUNT")
  private Long revisionCount;
}
