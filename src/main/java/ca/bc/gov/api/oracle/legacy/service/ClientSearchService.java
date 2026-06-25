package ca.bc.gov.api.oracle.legacy.service;

import static org.springframework.data.relational.core.query.Criteria.where;

import ca.bc.gov.api.oracle.legacy.dto.ClientPublicViewDto;
import ca.bc.gov.api.oracle.legacy.dto.SearchNumberScoreProjection;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import ca.bc.gov.api.oracle.legacy.repository.ForestClientRepository;
import ca.bc.gov.api.oracle.legacy.util.ClientMapper;
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

/** Provides search operations for client records. */
@Service
@RequiredArgsConstructor
@Slf4j
public class ClientSearchService {

  private final R2dbcEntityTemplate template;
  private final ForestClientRepository forestClientRepository;

  /**
   * Creates search criteria based on a list of client ids.
   *
   * @param ids the list of client ids to search for
   * @return the generated search criteria
   */
  public Criteria searchById(List<String> ids) {
    log.info("Searching for clients with ids {}", ids);

    Criteria queryCriteria = Criteria.empty();
    if (ids != null && !ids.isEmpty()) {
      queryCriteria = queryCriteria.and(where("clientNumber").in(ids));
    }
    return queryCriteria;
  }

  /**
   * Searches for clients using the provided query criteria.
   *
   * @param queryCriteria the criteria used to search for clients
   * @param page the page number of the clients to retrieve
   * @param size the number of clients to retrieve per page
   * @return a stream of matching clients
   */
  public Flux<ClientPublicViewDto> searchClientByQuery(
      Criteria queryCriteria, Integer page, Integer size) {
    Query searchQuery = Query.query(queryCriteria);

    log.info(
        "Searching for clients with query {} {}",
        queryCriteria,
        queryCriteria.isEmpty());

    if (queryCriteria.isEmpty()) {
      return Flux.empty();
    }

    return template
        .count(searchQuery, ForestClientEntity.class)
        .doOnNext(count -> log.info("Found {} clients", count))
        .flatMapMany(count ->
            template
                .select(
                    searchQuery
                        .with(PageRequest.of(page, size))
                        .sort(
                            Sort.by(Sort.Order.asc("clientNumber"))
                                .and(Sort.by(Sort.Order.asc("clientName")))),
                    ForestClientEntity.class)
                .map(ClientMapper::mapEntityToDto)
                .doOnNext(client -> client.setCount(count)))
        .doOnNext(client -> log.info("Found client with id {}", client.getClientNumber()));
  }

  /**
   * Constructs search criteria based on the provided client name, acronym, or number.
   *
   * @param name the name of the client to search for, or {@code null} if not specified
   * @param acronym the acronym of the client to search for, or {@code null} if not specified
   * @param number the unique number of the client to search for, or {@code null} if not specified
   * @param page the page number to retrieve
   * @param size the number of results to return
   * @return a {@link Mono} emitting the constructed {@link Criteria}
   * @implNote Input values are transformed to uppercase for case-insensitivity. The client number
   *     is validated using {@link #checkClientNumber(String)}. Repository results are mapped to a
   *     search criteria object.
   */
  public Mono<Criteria> searchByAcronymNameNumber(
      String name, String acronym, String number, int page, int size) {
    log.info(
        "Searching for clients with name {}, acronym {} or number {}",
        name,
        acronym,
        number);

    String searchName = StringUtils.isNotBlank(name) ? name.toUpperCase() : null;
    String searchAcronym = StringUtils.isNotBlank(acronym) ? acronym.toUpperCase() : null;
    String searchNumber = StringUtils.isNotBlank(number) ? checkClientNumber(number) : null;
    Pageable pageRequest = PageRequest.of(page, size);

    return forestClientRepository
        .searchNumberByNameAcronymNumber(
            searchName,
            searchAcronym,
            searchNumber,
            pageRequest.getOffset(),
            pageRequest.getPageSize())
        .map(SearchNumberScoreProjection::getClientNumber)
        .collectList()
        .map(this::searchById);
  }

  /**
   * Searches for clients based on the provided list of ids and an optional name filter.
   *
   * @param id a list of client ids to search for
   * @param name an optional name filter to refine results
   * @param page the page number to retrieve (0-based)
   * @param size the number of results per page
   * @return a stream of matching clients
   */
  public Flux<ClientPublicViewDto> searchByIdsAndName(
      List<String> id, String name, Integer page, Integer size) {
    log.info("Searching by ids {} and name {}, page: {}, size: {}", id, name, page, size);

    String searchName = StringUtils.isNotBlank(name) ? name.toUpperCase() : null;
    List<String> searchIds = !CollectionUtils.isEmpty(id) ? id : List.of();

    return forestClientRepository
        .searchByIdsAndName(searchIds, searchName, page * size.longValue(), size)
        .map(ClientMapper::mapEntityToDto);
  }

  /**
   * Normalizes a client number to the expected padded numeric format when possible.
   *
   * @param clientNumber the client number to normalize
   * @return the normalized client number, or the original value if parsing fails
   */
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
