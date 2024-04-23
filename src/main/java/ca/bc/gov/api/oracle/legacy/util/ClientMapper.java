package ca.bc.gov.api.oracle.legacy.util;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientViewDto;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This is a utility class that provides methods to map ForestClientEntity objects to ClientPublicViewDto and ClientViewDto objects.
 * It is annotated with @NoArgsConstructor(access = AccessLevel.PRIVATE) to prevent instantiation of this utility class.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

  /**
   * This method maps a ForestClientEntity object to a ClientPublicViewDto object.
   * It takes in a ForestClientEntity object and returns a ClientPublicViewDto object with the same client details.
   *
   * @param clientEntity The ForestClientEntity object to be mapped.
   * @return A ClientPublicViewDto object with the same client details as the provided ForestClientEntity object.
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

  /**
   * This method maps a ForestClientEntity object to a ClientPublicViewDto object and sets the count of total matching clients.
   * It takes in a ForestClientEntity object and a count of total matching clients and returns a ClientPublicViewDto object with the same client details and the count.
   *
   * @param clientEntity The ForestClientEntity object to be mapped.
   * @param count The count of total matching clients.
   * @return A ClientPublicViewDto object with the same client details as the provided ForestClientEntity object and the count of total matching clients.
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