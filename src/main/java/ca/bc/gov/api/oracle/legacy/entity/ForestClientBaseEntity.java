package ca.bc.gov.api.oracle.legacy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class ForestClientBaseEntity {
  @Id
  @Column("CLIENT_NUMBER")
  protected String clientNumber;

  @Column("CLIENT_NAME")
  protected String clientName;

  @Column("LEGAL_FIRST_NAME")
  protected String legalFirstName;

  @Column("LEGAL_MIDDLE_NAME")
  protected String legalMiddleName;

  @Column("CLIENT_STATUS_CODE")
  protected String clientStatusCode;

  @Column("CLIENT_TYPE_CODE")
  protected String clientTypeCode;
}
