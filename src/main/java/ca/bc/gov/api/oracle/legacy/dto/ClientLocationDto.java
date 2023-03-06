package ca.bc.gov.api.oracle.legacy.dto;

import java.time.LocalDate;

public record ClientLocationDto(
    String clientNumber,
    String locationCode,
    String locationName,
    String companyCode,
    String address1,
    String address2,
    String address3,
    String city,
    String province,
    String postalCode,
    String country,
    String businessPhone,
    String homePhone,
    String cellPhone,
    String faxNumber,
    String email,
    YesNoEnum expired,
    YesNoEnum trusted,
    LocalDate returnedMailDate,
    String comment
) {
}
