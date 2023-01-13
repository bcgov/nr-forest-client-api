package ca.bc.gov.api.oracle.legacy.dto;

import lombok.Builder;

@Builder
public record ClientPublicViewDto(
    String clientNumber, String clientName, String legalFirstName, String legalMiddleName,
    String clientStatusCode, String clientTypeCode) {
}
