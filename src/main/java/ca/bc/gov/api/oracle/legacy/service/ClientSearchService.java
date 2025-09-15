package ca.bc.gov.api.oracle.legacy.service;

import static org.springframework.data.relational.core.query.Criteria.where;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.SearchNumberScoreProjection;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import ca.bc.gov.api.oracle.legacy.repository.ForestClientRepository;
import ca.bc.gov.api.oracle.legacy.util.ClientMapper;
import java.time.Duration;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientSearchService {

  private final R2dbcEntityTemplate template;
  private final ForestClientRepository forestClientRepository;

  /**
   * This method is used to create a search criteria based on a list of client IDs. It first logs
   * the IDs of the clients to be searched. Then, it creates an empty query criteria. If the list of
   * IDs is not null and not empty, it adds a query criteria to search for clients with client
   * numbers in the IDs list. Finally, it returns the created search criteria.
   *
   * @param ids The list of client IDs to be used in the search criteria.
   * @return The created search criteria.
   */
  public Criteria searchById(List<String> ids) {
    log.info("Searching for clients with ids {}", ids);

    // Create an empty query criteria.
    Criteria queryCriteria = Criteria.empty();

    /* If the list of IDs is not empty, add a query criterion to search for clients 
       with client numbers in the list of IDs.*/
    if (ids != null && !ids.isEmpty()) {
      queryCriteria = queryCriteria
          .and(where("clientNumber").in(ids));
    }

    // Return search criteria
    return queryCriteria;
  }

  /**
   * This method is used to search for clients based on a given query criteria, page number, and
   * page size. It first creates a query based on the provided query criteria. Then, it counts the
   * total number of clients that match the search query. It retrieves the specific page of clients
   * based on the page number and size. The clients are sorted in ascending order by client number
   * and then by client name. Each retrieved client entity is then mapped to a DTO (Data Transfer
   * Object). The count of total matching clients is also set in each client DTO. Finally, it logs
   * the client number of each retrieved client.
   *
   * @param queryCriteria The criteria used to search for clients.
   * @param page          The page number of the clients to retrieve.
   * @param size          The number of clients to retrieve per page.
   * @return A Flux stream of ClientPublicViewDto objects.
   */
  public Flux<ClientPublicViewDto> searchClientByQuery(
      final Criteria queryCriteria,
      final Integer page,
      final Integer size
  ) {
    // Create a query based on the query criteria.
    Query searchQuery = Query.query(queryCriteria);

    log.info("Searching for clients with query {} {}",
        queryCriteria,
        queryCriteria.isEmpty()
    );

    if (queryCriteria.isEmpty()) {
      return Flux.empty();
    }

    // Count the total number of clients that match the search query.
    return template
        .count(searchQuery, ForestClientEntity.class)
        .doOnNext(count -> log.info("Found {} clients", count))
        // Retrieve the clients based on the search query, page number, and size.
        .flatMapMany(count ->
            template
                .select(
                    searchQuery
                        .with(PageRequest.of(page, size))
                        .sort(
                            Sort
                                .by(Sort.Order.asc("clientNumber"))
                                .and(Sort.by(Sort.Order.asc("clientName")))
                        ),
                    ForestClientEntity.class
                )
                // Map each client entity to a DTO and set the count of total matching clients.
                .map(ClientMapper::mapEntityToDto)
                // Add the total count on each retrieved client.
                .doOnNext(client -> client.setCount(count))
        )
        .doOnNext(client -> log.info("Found client with id {}", client.getClientNumber()));
  }

  /**
   * Constructs a search {@link Criteria} object based on the provided client name, acronym, or
   * number. This method normalizes input values for case-insensitive searches and validates the
   * client number.
   *
   * @param name    the name of the client to search for, or null if not specified.
   * @param acronym the acronym of the client to search for, or null if not specified.
   * @param number  the unique number of the client to search for, or null if not specified.
   * @param size    the number of results to return.
   * @return a {@link Mono} emitting the constructed {@link Criteria} object for the search.
   * @implNote Input values are transformed to uppercase for case-insensitivity. The client number
   * is validated using {@link #checkClientNumber(String)}. Repository results are mapped to a
   * search criteria object.
   */
  public Mono<Criteria> searchByAcronymNameNumber(
      String name,
      String acronym,
      String number,
      int page,
      int size) {
    log.info("Searching for clients with name {}, acronym {} or number {}", name, acronym, number);

    String searchName = StringUtils.isNotBlank(name) ? name.toUpperCase() : null;
    String searchAcronym = StringUtils.isNotBlank(acronym) ? acronym.toUpperCase() : null;
    String searchNumber = StringUtils.isNotBlank(number) ? checkClientNumber(number) : null;
    Pageable pageRequest = PageRequest.of(page, size);

    return
        forestClientRepository
            .searchNumberByNameAcronymNumber(
                searchName,
                searchAcronym,
                searchNumber,
                pageRequest.getOffset(),
                pageRequest.getPageSize()
            )
            .map(SearchNumberScoreProjection::getClientNumber)
            .collectList()
            .map(this::searchById);

  }
  /**
   * Searches for clients based on the provided list of IDs and an optional name filter. The search
   * results are paginated according to the specified page number and size.
   *
   * @param id   A list of client IDs to search for.
   * @param name An optional name filter to refine the search results.
   * @param page The page number to retrieve (0-based).
   * @param size The number of results per page.
   * @return A Flux stream of matching clients as ClientPublicViewDto objects.
   * @implNote This method is currently a placeholder and returns null. The implementation should
   * include logic to perform the search using the provided parameters and return the appropriate
   * results.
   */
  public Flux<ClientPublicViewDto> searchByIdsAndName(List<String> id, String name, Integer page, Integer size) {
    log.info("Searching by ids {} and name {}, page: {}, size: {}", id, name, page, size);

    String searchName = StringUtils.isNotBlank(name) ? name.toUpperCase() : null;
    List<String> searchIds = !CollectionUtils.isEmpty(id) ? id : List.of();

    return forestClientRepository
        .searchByIdsAndName(
            searchIds,
            searchName,
            (page * size.longValue()),
            size
        )
        .map(ClientMapper::mapEntityToDto);
  }

  private String checkClientNumber(String clientNumber) {
    if (StringUtils.isBlank(clientNumber)) {
      return clientNumber;
    }

    try {
      Integer parsed = Integer.parseInt(clientNumber);
      return String.format("%08d", parsed);
    } catch (NumberFormatException nfe) {
      return clientNumber;
    }
  }
}
