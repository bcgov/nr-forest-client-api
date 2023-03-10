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
@Table(name = "CLIENT_DOING_BUSINESS_AS", schema = "THE")
public class ClientDoingBusinessAsEntity {

  @Id
  @Column("CLIENT_DBA_ID")
  private Long id;

  @Column("CLIENT_NUMBER")
  private String clientNumber;

  @Column("DOING_BUSINESS_AS_NAME")
  private String name;
}
