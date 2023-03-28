package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import ca.bc.gov.api.oracle.legacy.entity.ForestClientEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ForestClientRepository extends ReactiveCrudRepository<ForestClientEntity, String>,
    ReactiveQueryByExampleExecutor<ForestClientEntity>,
    ReactiveSortingRepository<ForestClientEntity, String> {

  Flux<ForestClientEntity> findByClientAcronym(String acronym);

  Flux<ForestClientEntity> findByClientTypeCodeNot(String clientTypeCode, Pageable pageable);
}
