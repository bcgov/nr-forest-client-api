package ca.bc.gov.api.oracle.legacy.util;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientCountEntity;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This is a utility class that provides methods to map ForestClientEntity objects 
 * to ClientPublicViewDto and ClientViewDto objects.
 * It is annotated with @NoArgsConstructor(access = AccessLevel.PRIVATE) to 
 * prevent instantiation of this utility class.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

  /**
   * Maps a {@link ForestClientEntity} object to a {@link ClientPublicViewDto} object.
   * This method converts the provided {@link ForestClientEntity} into a {@link ClientPublicViewDto}, 
   * transferring the relevant client details.
   *
   * @param clientEntity the {@link ForestClientEntity} object to be mapped.
   * @return a {@link ClientPublicViewDto} object containing the same client details 
   *         as the provided {@link ForestClientEntity}.
   */
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

  public static ClientPublicViewDto mapEntityToDto(ForestClientCountEntity clientEntity) {
    return ClientPublicViewDto
        .builder()
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
   * Maps a {@link ForestClientEntity} object to a {@link ClientPublicViewDto} object and sets the
   * count of total matching clients. This method converts the provided {@link ForestClientEntity}
   * into a {@link ClientPublicViewDto}, transferring the relevant client details and adding the 
   * count of total matching clients.
   *
   * @param clientEntity the {@link ForestClientEntity} object to be mapped.
   * @param count the count of total matching clients.
   * @return a {@link ClientPublicViewDto} object containing the same client details as the provided
   *         {@link ForestClientEntity} and the count of total matching clients.
   */
  public static ClientPublicViewDto mapEntityToDto(ForestClientEntity clientEntity, Long count) {
    return ClientPublicViewDto
        .builder()
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
   * This method maps a ForestClientEntity object to a ClientViewDto object.
   * It takes in a ForestClientEntity object and returns a ClientViewDto object with the same client details.
   *
   * @param clientEntity The ForestClientEntity object to be mapped.
   * @return A ClientViewDto object with the same client details as the provided ForestClientEntity object.
   */
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