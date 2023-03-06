package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ClientDoingBusinessAsEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Mono;

public interface ClientDoingBusinessAsRepository extends
    ReactiveCrudRepository<ClientDoingBusinessAsEntity, Long>,
    ReactiveSortingRepository<ClientDoingBusinessAsEntity, Long>,
    ReactiveQueryByExampleExecutor<ClientDoingBusinessAsEntity> {

  Mono<ClientDoingBusinessAsEntity> findByName(String name);

}
