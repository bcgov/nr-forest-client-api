package ca.bc.gov.api.oracle.legacy.util;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientCountEntity;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/** Maps entity models to their corresponding client DTOs. */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

  /**
   * Maps a {@link ForestClientEntity} to a {@link ClientPublicViewDto}.
   *
   * @param clientEntity the entity to map
   * @return the mapped DTO
   */
  public static ClientPublicViewDto mapEntityToDto(ForestClientEntity clientEntity) {
    return ClientPublicViewDto.builder()
        .clientNumber(clientEntity.getClientNumber())
        .clientName(clientEntity.getClientName())
        .legalFirstName(clientEntity.getLegalFirstName())
        .legalMiddleName(clientEntity.getLegalMiddleName())
        .clientStatusCode(clientEntity.getClientStatusCode())
        .clientTypeCode(clientEntity.getClientTypeCode())
        .acronym(clientEntity.getClientAcronym())
        .build();
  }

  /**
   * Maps a counted entity projection to a {@link ClientPublicViewDto}.
   *
   * @param clientEntity the entity to map
   * @return the mapped DTO including count information
   */
  public static ClientPublicViewDto mapEntityToDto(ForestClientCountEntity clientEntity) {
    return ClientPublicViewDto.builder()
        .clientNumber(clientEntity.getClientNumber())
        .clientName(clientEntity.getClientName())
        .legalFirstName(clientEntity.getLegalFirstName())
        .legalMiddleName(clientEntity.getLegalMiddleName())
        .clientStatusCode(clientEntity.getClientStatusCode())
        .clientTypeCode(clientEntity.getClientTypeCode())
        .acronym(clientEntity.getClientAcronym())
        .count(clientEntity.getCount())
        .build();
  }

  /**
   * Maps a {@link ForestClientEntity} to a {@link ClientPublicViewDto} and sets the count.
   *
   * @param clientEntity the entity to map
   * @param count the total matching count
   * @return the mapped DTO including count information
   */
  public static ClientPublicViewDto mapEntityToDto(ForestClientEntity clientEntity, Long count) {
    return ClientPublicViewDto.builder()
        .clientNumber(clientEntity.getClientNumber())
        .clientName(clientEntity.getClientName())
        .legalFirstName(clientEntity.getLegalFirstName())
        .legalMiddleName(clientEntity.getLegalMiddleName())
        .clientStatusCode(clientEntity.getClientStatusCode())
        .clientTypeCode(clientEntity.getClientTypeCode())
        .acronym(clientEntity.getClientAcronym())
        .count(count)
        .build();
  }

  /**
   * Maps a {@link ForestClientEntity} to a {@link ClientViewDto}.
   *
   * @param clientEntity the entity to map
   * @return the mapped detailed client DTO
   */
  public static ClientViewDto mapEntityToClientViewDto(ForestClientEntity clientEntity) {
    return ClientViewDto.builder()
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
