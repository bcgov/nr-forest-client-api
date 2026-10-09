package ca.bc.gov.api.oracle.legacy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import lombok.experimental.SuperBuilder;

/** Extends the public client view with descriptive code labels. */
@Data
@With
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ClientViewDto extends ClientPublicViewDto {
  private String clientStatusCodeDescription;
  private String clientTypeCodeDescription;
}
