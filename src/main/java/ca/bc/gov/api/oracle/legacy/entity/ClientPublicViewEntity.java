package ca.bc.gov.api.oracle.legacy.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@With
@Builder
//@Entity
@Table(name = "V_CLIENT_PUBLIC", schema = "THE")
public class ClientPublicViewEntity {

  public static final String INDIVIDUAL = "I";

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
}
