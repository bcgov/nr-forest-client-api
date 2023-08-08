package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.dto.ClientLocationDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.ClientViewDto;
import ca.bc.gov.api.oracle.legacy.service.ClientLocationService;
import ca.bc.gov.api.oracle.legacy.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@Tag(
    name = "Client API",
    description = "Deals with client data checks and validation"
)
@RequestMapping(value = "/api/clients", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;
  private final ClientLocationService locationService;

  @GetMapping("/findByClientNumber/{clientNumber}")
  @Operation(
      summary = "Search clients by client number. It will return active and inactive",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a client based on it's number",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(
                      name = "ClientView",
                      implementation = ClientPublicViewDto.class
                  )
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
  public Mono<ClientPublicViewDto> findByClientNumber(
      @Parameter(
          description = "The client number to look for",
          example = "00000002"
      )
      @PathVariable String clientNumber
  ) {
    return clientService.findByClientNumber(clientNumber);
  }

  @GetMapping("/findByClientNumberOrName/{clientNumberOrName}")
  @Operation(
      summary = "Search clients by client number or client name. It will return active and inactive",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a client based on it's number or name",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(
                      name = "ClientView",
                      implementation = ClientPublicViewDto.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "Unable find a client based on the provided parameter",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class),
                  examples = {@ExampleObject(value = "Client not found")}
              )
          )
      }
  )
  public Flux<ClientViewDto> findByClientNumberOrName(
      @Parameter(description = "The one index page number, defaults to 0", example = "0")
      @RequestParam(value = "page", required = false, defaultValue = "0")
      Integer page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(value = "size", required = false, defaultValue = "10")
      Integer size,

      @Parameter(
          description = "The client number to look for",
          example = "00000002"
      )
      @PathVariable String clientNumberOrName
  ) {
    return clientService.findByClientNumberOrName(page, size, clientNumberOrName);
  }

  @GetMapping("/findAllNonIndividuals")
  @Operation(
      summary = "Search all non-individual client. It will return active and inactive",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a list of  client",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(
                          name = "ClientView",
                          implementation = ClientPublicViewDto.class
                      )
                  )
              )
          )
      }
  )
  public Flux<ClientPublicViewDto> findAllNonIndividuals(
      @Parameter(description = "The one index page number, defaults to 0", example = "0")
      @RequestParam(value = "page", required = false, defaultValue = "0")
      Integer page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(value = "size", required = false, defaultValue = "10")
      Integer size,

      @Parameter(
          description = "Column name to sort by, defaults to clientName",
          example = "clientName")
      @RequestParam(value = "sortedColumnName", required = false, defaultValue = "clientName")
      String sortedColumn) {
    return clientService.findAllNonIndividualClients(page, size, sortedColumn);
  }

  @GetMapping("/findByNames")
  @Operation(
      summary = """
          Search a client by it's name (including first, middle and last) and client type. 
          It will return active and inactive""",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a list of  client based on provided parameters",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(
                          name = "ClientView",
                          implementation = ClientPublicViewDto.class
                      )
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
  public Flux<ClientPublicViewDto> findByNames(
      @Parameter(description = "The one index page number, defaults to 0", example = "0")
      @RequestParam(value = "page", required = false, defaultValue = "0")
      Integer page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(value = "size", required = false, defaultValue = "10")
      Integer size,

      @Parameter(
          description = "The name of the entity or individual's last name",
          example = "Baxter")
      @RequestParam(value = "clientName", required = false)
      String clientName,

      @Parameter(
          description = "The client's first name",
          example = "James")
      @RequestParam(value = "clientFirstName", required = false)
      String clientFirstName,

      @Parameter(
          description = "The client's middle name",
          example = "Canter")
      @RequestParam(value = "clientMiddleName", required = false)
      String clientMiddleName,

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
          U (Unregistered Company),<br>
          Z (Sole Proprietorship)""",
          example = "I")
      @RequestParam(value = "clientTypeCodes", required = false)
      List<String> clientTypeCodes
  ) {
    return clientService.searchByNames(
        clientName,
        clientFirstName,
        clientMiddleName,
        clientTypeCodes,
        page,
        size
    );
  }

  @GetMapping("/findByAcronym")
  @Operation(
      summary = "Search a client by it's acronym. It will return active and inactive",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a client based on it's acronym",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(
                          name = "ClientView",
                          implementation = ClientPublicViewDto.class
                      )
                  )
              )
          ),
          @ApiResponse(
              responseCode = "400",
              description = "If no parameter is provided",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class),
                  examples = {@ExampleObject(value = "No acronym parameter found")}
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "If no client found for that acronym",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class),
                  examples = {@ExampleObject(value = "No client found with the acronym Baxter")}
              )
          )
      }
  )
  public Flux<ClientPublicViewDto> findByAcronym(
      @Parameter(
          description = "The acronym to look for",
          example = "Baxter")
      @RequestParam(value = "acronym")
      @NotNull
      String acronym
  ) {
    return clientService.searchByAcronym(acronym);
  }

  @GetMapping("/{clientNumber}/locations")
  @Operation(
      summary = "Search for client location based on client number",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a list of client locations",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(
                          name = "ClientLocation",
                          implementation = ClientLocationDto.class
                      )
                  )
              )
          )
      }
  )
  public Flux<ClientLocationDto> listClientLocations(
      @Parameter(description = "The one index page number, defaults to 0", example = "0")
      @RequestParam(value = "page", required = false, defaultValue = "0")
      Integer page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(value = "size", required = false, defaultValue = "10")
      Integer size,

      @Parameter(
          description = "ID of the client to filter by",
          example = "00000001")
      @PathVariable(value = "clientNumber")
      String clientNumber,
      ServerHttpResponse serverResponse
  ) {

    return
        locationService
            .listClientLocations(clientNumber, page, size);
  }

  @GetMapping("/{clientNumber}/locations/{locationNumber}")
  @Operation(
      summary = "Get the client location based on client number and location id",
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Returns a client location",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(
                      name = "ClientLocation",
                      implementation = ClientLocationDto.class
                  )
              )
          ),
          @ApiResponse(
              responseCode = "404",
              description = "If no client location found for that client number and location id",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = String.class),
                  examples = {@ExampleObject(value = "No client location found")}
              )
          )
      }
  )
  public Mono<ClientLocationDto> getClientLocationDetails(
      @Parameter(
          description = "ID of the client to filter by",
          example = "00000001")
      @PathVariable(value = "clientNumber")
      String clientNumber,
      @Parameter(
          description = "ID of the client location to filter by",
          example = "00000001")
      @PathVariable(value = "locationNumber")
      String locationNumber
  ) {
    return locationService.getClientLocationDetails(clientNumber, locationNumber);
  }
}
