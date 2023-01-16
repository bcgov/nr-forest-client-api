package ca.bc.gov.api.oracle.legacy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "V_CLIENT_PUBLIC", schema = "THE")
public class ClientPublicViewEntity {

  public static final String INDIVIDUAL = "I";

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
}
