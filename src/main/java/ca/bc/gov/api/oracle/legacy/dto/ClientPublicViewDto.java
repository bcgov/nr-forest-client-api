package ca.bc.gov.api.oracle.legacy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ClientPublicViewDto {

  public String clientNumber;
  public String clientName;
  public String legalFirstName;
  public String legalMiddleName;
  public String clientStatusCode;
  public String clientTypeCode;
}
