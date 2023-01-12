package ca.bc.gov.api.oracle.legacy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public record ClientPublicViewDto(
    String clientNumber, String clientName, String legalFirstName, String legalMiddleName,
    String clientStatusCode, String clientTypeCode) {
}
