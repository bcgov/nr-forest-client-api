package ca.bc.gov.api.oracle.legacy.entity;

import java.time.LocalDate;
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
@Table(name = "CLIENT_DOING_BUSINESS_AS", schema = "THE")
public class ClientDoingBusinessAsEntity {

  @Id
  @Column("CLIENT_DBA_ID")
  private Long id;

  @Column("CLIENT_NUMBER")
  private String clientNumber;

  @Column("DOING_BUSINESS_AS_NAME")
  private String name;

  @Column("UPDATE_TIMESTAMP")
  private LocalDate updatedAt;

  @Column("UPDATE_USERID")
  private String updatedBy;

  @Column("UPDATE_ORG_UNIT")
  Long updatedByOrg;

  @Column("ADD_TIMESTAMP")
  private LocalDate createdAt;

  @Column("ADD_USERID")
  private String createdBy;

  @Column("ADD_ORG_UNIT")
  Long createdByOrg;

  @Column("REVISION_COUNT")
  Long revision;

}
