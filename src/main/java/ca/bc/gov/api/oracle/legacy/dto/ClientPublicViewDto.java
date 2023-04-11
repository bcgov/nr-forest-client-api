package ca.bc.gov.api.oracle.legacy.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.With;

@Builder
@With
@Schema(
    description = "A snapshot of the client information",
    title = "ClientDetails",
    example = """
        {
          "clientNumber": "00000002",
          "clientName": "BAXTER",
          "legalFirstName": "JAMES",
          "legalMiddleName": "Canter",
          "clientStatusCode": "ACT",
          "clientTypeCode": "I",
          "acronym":"JAMES BAXTER"
        }"""
)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientPublicViewDto(
    @Schema(description = "The client number", example = "00000002")
    String clientNumber,

    @Schema(description = """
        The client last name if it's an individual or
         the company name if it's a company""",
        example = "BAXTER"
    )
    String clientName,

    @Schema(description = """
        The first name of the individual,
         or null if it's a company""",
        example = "JAMES")
    String legalFirstName,

    @Schema(description = """
        The middle name of the individual,
         or null if it's a company""",
        example = "Canter")
    String legalMiddleName,

    @Schema(description = """
        The status of the client, can be any of the following:<br>
                
        ACT (Active)<br>
        DAC (Deactivated)<br>
        DEC (Deceased)<br>
        REC (Receivership)<br>
        SPN (Suspended)""",
        example = "ACT")
    String clientStatusCode,

    @Schema(description = """
        The type of client, can be any of the following:<br>
                
        A (Association)<br>
        B (First Nation Band)<br>
        C (Corporation)<br>
        F (Ministry of Forests and Range)<br>
        G (Government)<br>
        I (Individual)<br>
        L (Limited Partnership)<br>
        P (General Partnership)<br>
        R (First Nation Group)<br>
        S (Society)<br>
        T (First Nation Tribal Council)<br>
        U (Unregistered Company)""",
        example = "I")
    String clientTypeCode,
    @Schema(description = "An acronyms for this client",
        example = "JAMES BAXTER")
    String acronym
) {
}
