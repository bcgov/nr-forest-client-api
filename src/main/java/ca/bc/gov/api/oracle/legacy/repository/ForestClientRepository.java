package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
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


	@Query(value = """
    SELECT
      CLIENT_NUMBER
    FROM THE.FOREST_CLIENT
    WHERE
      UTL_MATCH.JARO_WINKLER_SIMILARITY(CLIENT_NAME, :clientName) >= 80
      OR UTL_MATCH.JARO_WINKLER_SIMILARITY(LEGAL_FIRST_NAME, :clientName) >= 80
      OR UTL_MATCH.JARO_WINKLER_SIMILARITY(LEGAL_MIDDLE_NAME, :clientName) >= 80
      OR UTL_MATCH.JARO_WINKLER_SIMILARITY(TRIM(COALESCE(LEGAL_FIRST_NAME || ' ', '') || TRIM(COALESCE(LEGAL_MIDDLE_NAME || ' ', '')) || COALESCE(CLIENT_NAME, '')), :clientName) >= 80
      OR CLIENT_ACRONYM = :acronym
      OR CLIENT_NUMBER = :clientNumber
    ORDER BY CLIENT_NUMBER""")
	Flux<String> searchNumberByNameAcronymNumber(String clientName, String acronym, String clientNumber);

}
