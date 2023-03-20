package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ClientDoingBusinessAsEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;

public interface ClientDoingBusinessAsRepository extends
    ReactiveCrudRepository<ClientDoingBusinessAsEntity, Long>,
    ReactiveSortingRepository<ClientDoingBusinessAsEntity, Long>,
    ReactiveQueryByExampleExecutor<ClientDoingBusinessAsEntity> {

  Flux<ClientDoingBusinessAsEntity> findByName(String name);
  Flux<ClientDoingBusinessAsEntity> findByClientNumber(String clientNumber);

}
