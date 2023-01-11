package ca.bc.gov.api.oracle.legacy.util;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {
  public static ClientPublicViewDto mapEntityToDto(ClientPublicViewEntity clientEntity) {
    return ClientPublicViewDto
        .builder()
        .clientNumber(clientEntity.getClientNumber())
        .clientName(clientEntity.getClientName())
        .legalFirstName(clientEntity.getLegalFirstName())
        .legalMiddleName(clientEntity.getLegalMiddleName())
        .clientStatusCode(clientEntity.getClientStatusCode())
        .clientTypeCode(clientEntity.getClientTypeCode())
        .build();
  }
}
