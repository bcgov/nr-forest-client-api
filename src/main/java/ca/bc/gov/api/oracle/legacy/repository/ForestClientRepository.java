package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.dto.SearchNumberScoreProjection;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientCountEntity;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ForestClientRepository extends ReactiveCrudRepository<ForestClientEntity, String>,
    ReactiveQueryByExampleExecutor<ForestClientEntity>,
    ReactiveSortingRepository<ForestClientEntity, String> {

  Flux<ForestClientEntity> findByClientTypeCodeNot(String clientTypeCode, Pageable pageable);

  Mono<Long> countByClientTypeCodeNot(String clientTypeCode);

  Flux<ForestClientEntity> findByClientAcronym(String acronym);

  Flux<ForestClientEntity> findByClientNumberContainingOrClientNameContaining(
      String clientNumber, String clientName, Pageable pageable);

  Mono<Long> countByClientNumberContainingOrClientNameContaining(String clientNumber,
      String clientName);


  @Query(value = QueryConstants.SEARCH_NUMBER_NAME_ACRONYM)
	Flux<SearchNumberScoreProjection> searchNumberByNameAcronymNumber(
      String clientName,
      String acronym,
      String clientNumber,
      long offset,
      int size);

  @Query(value = QueryConstants.SEARCH_BY_NUMBER_AND_NAME)
  Flux<ForestClientCountEntity> searchByIdsAndName(
      List<String> clientNumbers,
      String clientName,
      long offset,
      int size
  );
}
