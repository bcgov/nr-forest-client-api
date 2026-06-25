package ca.bc.gov.api.oracle.legacy.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.With;
import lombok.experimental.SuperBuilder;

/** Extends the public client view with descriptive code labels. */
@Data
@Getter(AccessLevel.PROTECTED)
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
