package ca.bc.gov.api.oracle.legacy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;
import lombok.Builder;

@Builder
@Schema(
    description = "A location associated to a client",
    title = "ClientLocation",
    example = """
        {
          "clientNumber": "00000001",
          "locationCode": "00",
          "locationName": "Office",
          "companyCode": "01382",
          "address1": "2080 Labieux Rd",
          "city": "NANAIMO",
          "province": "BC",
          "postalCode": "V9T6J9",
          "country": "CANADA",
          "homePhone": "8006618773",
          "expired": "N",
          "trusted": "N"
        }"""
)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientLocationDto(
    @Schema(description = "The client number", example = "00000002")
    String clientNumber,
    @Schema(description = "The location index", example = "00")
    String locationCode,
    @Schema(description = "The reference name of the location", example = "Office",nullable = true)
    String locationName,
    @Schema(description = "The internal company code", example = "01234")
    String companyCode,
    @Schema(description = "The address information", example = "555 Unknown Rd")
    String address1,
    @Schema(description = "The address information", example = "Side Parkway",nullable = true)
    String address2,
    @Schema(description = "The address information", example = "Spot 3",nullable = true)
    String address3,
    @Schema(description = "The city name", example = "VICTORIA")
    String city,
    @Schema(description = "The province/territory code", example = "BC")
    String province,
    @Schema(description = "The postal code/zip code", example = "V9T6J6")
    String postalCode,
    @Schema(description = "The country code", example = "CA")
    String country,
    @Schema(description = "The business phone number", example = "555 555 5555",nullable = true)
    String businessPhone,
    @Schema(description = "The home/personal phone number", example = "555 555 5555",nullable = true)
    String homePhone,
    @Schema(description = "The cellphone number", example = "555 555 5555",nullable = true)
    String cellPhone,
    @Schema(description = "The fax number", example = "555 555 5555",nullable = true)
    String faxNumber,
    @Schema(description = "The email address", example = "555 555 5555",nullable = true)
    String email,
    @Schema(description = "Define if this entry is expired or not<br>Y means yes<br>N means no",
        example = "Y"
    )
    YesNoEnum expired,
    @Schema(description = "Define if this entry is to be trusted or not<br>Y means yes<br>N means no",
        example = "N"
    )
    YesNoEnum trusted,
    @Schema(description = "The date when the mail was returned", example = "2012-05-14",nullable = true)
    LocalDate returnedMailDate,
    @Schema(description = "An open field containing comments about the address",
        example = "It is used just as a mail address, residential address",
        nullable = true
    )
    String comment
) {
}
