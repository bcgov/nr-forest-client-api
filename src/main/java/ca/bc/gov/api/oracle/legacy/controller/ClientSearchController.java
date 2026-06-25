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

/** Handles client search endpoints. */
@RestController
@Slf4j
@Tag(name = "Client Search API", description = "Deals with search on client data")
@RequestMapping(value = "/api/clients/search", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientSearchController {

  private final ClientSearchService clientSearchService;

  /**
   * Searches for clients based on client ids and an optional name filter.
   *
   * @param page the zero-based page number, defaults to 0
   * @param size the number of results per page, defaults to 10
   * @param id the client ids to search for
   * @param name the optional name filter
   * @param serverResponse the HTTP response used to set the total-count header
   * @return a stream of matching clients
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
                          implementation = ClientPublicViewDto.class))
              ),
              headers = {
                  @Header(
                      name = ApplicationConstants.X_TOTAL_COUNT,
                      description = "Total number of records found")
              })
      })
  public Flux<ClientPublicViewDto> searchClients(
      @Parameter(description = "The one index page number, defaults to 0", example = "0")
      @RequestParam(value = "page", required = false, defaultValue = "0")
      Integer page,
      @Parameter(
          description = "The amount of data to be returned per page, defaults to 10",
          example = "10")
      @RequestParam(value = "size", required = false, defaultValue = "10")
      Integer size,
      @Parameter(description = "Id of the client you're searching", example = "00000001")
      @RequestParam(value = "id")
      List<String> id,
      @Parameter(description = "Name of the client you want to filter by", example = "BOND")
      @RequestParam(value = "name", required = false)
      String name,
      ServerHttpResponse serverResponse) {
    log.info("Searching for clients with ids {}", id);
    return clientSearchService
        .searchByIdsAndName(id, name, page, size)
        .doOnNext(client -> log.info("Found client with id {}", client.getClientNumber()))
        .doOnNext(dto ->
            serverResponse
                .getHeaders()
                .putIfAbsent(
                    ApplicationConstants.X_TOTAL_COUNT,
                    List.of(dto.getCount().toString())));
  }

  /**
   * Searches for clients using fuzzy matching on name, acronym, and number values.
   *
   * @param page the zero-based page number, defaults to 0
   * @param size the number of results per page, defaults to 10
   * @param name the optional client name filter
   * @param acronym the optional client acronym filter
   * @param number the optional client number filter
   * @param serverResponse the HTTP response used to set the total-count header
   * @return a stream of matching clients
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
                          implementation = ClientPublicViewDto.class))
              ),
              headers = {
                  @Header(
                      name = ApplicationConstants.X_TOTAL_COUNT,
                      description = "Total number of records found")
              })
      })
  public Flux<ClientPublicViewDto> searchByAcronymNameNumber(
      @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
      @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
      @RequestParam(value = "name", required = false) String name,
      @RequestParam(value = "acronym", required = false) String acronym,
      @RequestParam(value = "number", required = false) String number,
      ServerHttpResponse serverResponse) {
    log.info(
        "Searching for clients with name='{}', acronym='{}', number='{}'",
        name,
        acronym,
        number);

    return clientSearchService
        .searchByAcronymNameNumber(name, acronym, number, page, size)
        .flatMapMany(criteria -> clientSearchService.searchClientByQuery(criteria, page, size))
        .doOnNext(client ->
            serverResponse
                .getHeaders()
                .putIfAbsent(
                    ApplicationConstants.X_TOTAL_COUNT,
                    List.of(client.getCount() != null ? client.getCount().toString() : "0")));
  }
}
