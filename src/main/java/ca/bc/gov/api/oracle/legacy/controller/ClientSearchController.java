package ca.bc.gov.api.oracle.legacy.controller;

import ca.bc.gov.api.oracle.legacy.ApplicationConstants;
import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.service.ClientSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * This is the main controller for the Client Search API. It is a REST controller that handles
 * requests to the /api/clients/search endpoint. It uses the ClientSearchService to perform
 * operations related to client search. It is annotated with @RestController, indicating that it is
 * a controller where every method returns a domain object instead of a view. It is also annotated
 * with @Slf4j, which provides a logger for the class to log information. The @Tag annotation
 * provides additional metadata for the API documentation. The @RequestMapping annotation maps
 * requests to the /api/clients/search endpoint to this controller. The @RequiredArgsConstructor
 * annotation generates a constructor with 1 parameter for each field that requires special
 * handling.
 */
@RestController
@Slf4j
@Tag(
    name = "Client Search API",
    description = "Deals with search on client data"
)
@RequestMapping(value = "/api/clients/search", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientSearchController {

  private final ClientSearchService clientSearchService;

  /**
   * This is a GET mapping for the searchClients endpoint. It searches for clients based on the
   * provided client IDs, page number, and page size. It first logs the IDs of the clients to be
   * searched. Then, it calls the searchClientByQuery method of the clientSearchService to retrieve
   * the clients. The searchClientByQuery method takes in a search criteria created by the
   * searchById method of the clientSearchService, the page number, and the page size. It logs the
   * client number of each retrieved client. It also sets the X_TOTAL_COUNT header of the server
   * response to the count of the retrieved clients. Finally, it returns a Flux stream of
   * ClientPublicViewDto objects.
   *
   * @param page           The one index page number, defaults to 0.
   * @param size           The amount of data to be returned per page, defaults to 10.
   * @param id             The IDs of the clients to be searched.
   * @param serverResponse The server response to which the X_TOTAL_COUNT header is to be set.
   * @return A Flux stream of ClientPublicViewDto objects.
   */
  @GetMapping
  @Operation(
      summary = "Search for clients",
      description = "Search for clients based on the provided client IDs",
      tags = {"Client Search API"},
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully retrieved clients",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(
                          name = "ClientView",
                          implementation = ClientPublicViewDto.class
                      )
                  )
              ),
              headers = {
                  @Header(
                      name = ApplicationConstants.X_TOTAL_COUNT,
                      description = "Total number of records found"
                  )
              }
          )
      }
  )
  public Flux<ClientPublicViewDto> searchClients(
      @Parameter(description = "The one index page number, defaults to 0", example = "0")
      @RequestParam(value = "page", required = false, defaultValue = "0")
      Integer page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(value = "size", required = false, defaultValue = "10")
      Integer size,

      @Parameter(description = "Id of the client you're searching", example = "00000001")
      @RequestParam(value = "id")
      List<String> id,

      @Parameter(description = "Name of the client you want to filter by", example = "BOND")
      @RequestParam(value = "name", required = false)
      String name,

      ServerHttpResponse serverResponse
  ) {
    log.info("Searching for clients with ids {}", id);
    return
        clientSearchService
            .searchByIdsAndName(id, name,page,size)
        .doOnNext(client -> log.info("Found client with id {}", client.getClientNumber()))
        .doOnNext(dto -> serverResponse.getHeaders()
            .putIfAbsent(ApplicationConstants.X_TOTAL_COUNT, List.of(dto.getCount().toString())));
  }

  /**
   * Searches for clients based on the provided parameters using a fuzzy match algorithm.
   * The search is case-insensitive and has a threshold cutout of 0.8 for the fuzzy match.
   *
   * @param page the one-based page number to retrieve, defaults to 0 if not provided.
   * @param size the number of results per page, defaults to 10 if not provided.
   * @param name the name of the client to search for (optional).
   * @param acronym the acronym of the client to search for (optional).
   * @param number the unique number of the client to search for (optional).
   * @param serverResponse the {@link ServerHttpResponse} to include response headers.
   * @return a reactive stream of {@link ClientPublicViewDto} objects representing matching
   *         clients.
   *
   * @apiNote This method provides a paginated, fuzzy search for client details. Results
   *          include a total record count in the response headers under {@code X-Total-Count}.
   */
  @GetMapping("/by")
  @Operation(
      summary = "Search for clients",
      description = """
          Search for clients based on the provided parameters.
          It uses a fuzzy match to search for the client name.
          The cutout for the fuzzy match is 0.8. The search is case insensitive.""",
      tags = {"Client Search API"},
      responses = {
          @ApiResponse(
              responseCode = "200",
              description = "Successfully retrieved clients",
              content = @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  array = @ArraySchema(
                      schema = @Schema(
                          name = "ClientView",
                          implementation = ClientPublicViewDto.class
                      )
                  )
              ),
              headers = {
                  @Header(
                      name = ApplicationConstants.X_TOTAL_COUNT,
                      description = "Total number of records found"
                  )
              }
          )
      }
  )
  public Flux<ClientPublicViewDto> searchByAcronymNameNumber(
      @Parameter(description = "The one index page number, defaults to 0", 
                 example = "0")
      @RequestParam(value = "page", required = false, defaultValue = "0")
      Integer page,

      @Parameter(description = "The amount of data to be returned per page, defaults to 10",
                 example = "10")
      @RequestParam(value = "size", required = false, defaultValue = "10")
      Integer size,

      @Parameter(description = "The name of the client you're searching", 
                 example = "Western Forest Products")
      @RequestParam(value = "name", required = false)
      String name,

      @Parameter(description = "The acronym of the client you're searching", 
                 example = "WFPS")
      @RequestParam(value = "acronym", required = false)
      String acronym,

      @Parameter(description = "The number of the client you're searching", 
                 example = "00000001")
      @RequestParam(value = "number", required = false)
      String number,

      ServerHttpResponse serverResponse
  ) {

    log.info("Searching for clients with name {}, acronym {}, number {}", name, acronym, number);
    return
        clientSearchService
            .searchByAcronymNameNumber(name, acronym, number, page, size)
            .flatMapMany(criteria -> clientSearchService.searchClientByQuery(criteria, page, size))
            .doOnNext(client -> log.info("Found client with id {}", client.getClientNumber()))
            .doOnNext(dto -> serverResponse.getHeaders()
                .putIfAbsent(ApplicationConstants.X_TOTAL_COUNT,
                    List.of(dto.getCount().toString())));

  }

}
