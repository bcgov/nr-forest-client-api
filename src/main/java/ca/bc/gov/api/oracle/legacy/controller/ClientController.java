package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600, value = "*")
@RestController
@RequestMapping("/api/clients")
@Tag(
    name = "Client API",
    description = "Deals with client data checks and validation"
)
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @GetMapping(value = "/findByClientNumber/{clientNumber}")
  @Operation(
      summary = "Search a client by it's client number",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a client based on it's number",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = ClientPublicViewDto.class)
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "Client number is invalid, usually not a numeric value",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class),
                  examples = {@ExampleObject(value = "Invalid Client Number")}
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Unable find a client based on the provided id",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class),
                  examples = {@ExampleObject(value = "Client not found")}
              )
          )
      }
  )
  public ResponseEntity<ClientPublicViewDto> findByClientNumber(
      @Parameter(
          description = "The client number to look for",
          example = "00000002"
      )
      @PathVariable String clientNumber
  ) {
    return ResponseEntity.ok(clientService.findByClientNumber(clientNumber));
  }

  @GetMapping("/findAllNonIndividuals")
  @Operation(
      summary = "Search all non-individual client",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a list of  client",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(implementation = ClientPublicViewDto.class)
                  )
              )
          )
      }
  )
  public ResponseEntity<List<ClientPublicViewDto>> findAllNonIndividuals(
      @Parameter(description = "The one index page number, defaults to 0", example = "0")
      @RequestParam(defaultValue = "0") int page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(defaultValue = "10") int size,
      @Parameter(
          description = "Column name to sort by, defaults to clientName",
          example = "clientName")
      @RequestParam(defaultValue = "clientName") String sortedColumnName) {

    return ResponseEntity.ok(
        clientService.findAllNonIndividualClients(page, size, sortedColumnName));
  }

  @GetMapping(value = "/findByNames")
  @Operation(
      summary = "Search a client by it's name (including first, middle and last) and client type",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a list of  client based on provided parameters",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(implementation = ClientPublicViewDto.class)
                  )
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "If no parameter is provided",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class),
                  examples = {@ExampleObject(value = "No search parameter found")}
              )
          )
      }
  )
  public ResponseEntity<List<ClientPublicViewDto>> findByNames(
      @RequestParam(required = false)
      @Parameter(
          description = "The name of the entity or individual's last name",
          example = "Baxter")
      String clientName,

      @RequestParam(required = false)
      @Parameter(
          description = "The client's first name",
          example = "James")
      String clientFirstName,

      @RequestParam(required = false)
      @Parameter(
          description = "The client's middle name",
          example = "Canter")
      String clientMiddleName,

      @RequestParam(required = false)
      @Parameter(description = """
          The type of client, can be any of the following:<br>
                  
          A (Association),<br>
          B (First Nation Band),<br>
          C (Corporation),<br>
          F (Ministry of Forests and Range),<br>
          G (Government),<br>
          I (Individual),<br>
          L (Limited Partnership),<br>
          P (General Partnership),<br>
          R (First Nation Group),<br>
          S (Society),<br>
          T (First Nation Tribal Council),<br>
          U (Unregistered Company)""",
          example = "I")
      List<String> clientTypeCodes,

      @Parameter(description = "The one index page number, defaults to 1", example = "0")
      @RequestParam(defaultValue = "0") int page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(defaultValue = "10") int size) {

    return ResponseEntity.ok(
        clientService.searchByNames(
            clientName,
            clientFirstName,
            clientMiddleName,
            clientTypeCodes,
            page,
            size));
  }
}
