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
@Table(name = "V_CLIENT_PUBLIC", schema = "THE")
public class ClientPublicViewEntity extends ForestClientBaseEntity {

  public static final String INDIVIDUAL = "I";
}
