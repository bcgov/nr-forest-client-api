package ca.bc.gov.api.oracle.legacy.util;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {
  public static ClientPublicViewDto mapEntityToDto(ForestClientEntity clientEntity) {
    return ClientPublicViewDto
        .builder()
        .clientNumber(clientEntity.getClientNumber())
        .clientName(clientEntity.getClientName())
        .legalFirstName(clientEntity.getLegalFirstName())
        .legalMiddleName(clientEntity.getLegalMiddleName())
        .clientStatusCode(clientEntity.getClientStatusCode())
        .clientTypeCode(clientEntity.getClientTypeCode())
        .acronym(clientEntity.getClientAcronym())
        .build();
  }

  public static ClientViewDto mapEntityToClientViewDto(ForestClientEntity clientEntity) {
    return ClientViewDto
        .builder()
        .clientNumber(clientEntity.getClientNumber())
        .clientName(clientEntity.getClientName())
        .legalFirstName(clientEntity.getLegalFirstName())
        .legalMiddleName(clientEntity.getLegalMiddleName())
        .clientStatusCode(clientEntity.getClientStatusCode())
        .clientTypeCode(clientEntity.getClientTypeCode())
        .acronym(clientEntity.getClientAcronym())
        .build();
  }
}
