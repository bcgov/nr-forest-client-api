package ca.bc.gov.api.oracle.legacy.util;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

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
        .acronyms(getAcronyms(clientEntity.getClientAcronym()))
        .build();
  }

  private static List<String> getAcronyms(String acronym){
    if(StringUtils.isBlank(acronym))
      return List.of();
    return List.of(acronym);
  }
}
