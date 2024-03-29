package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ClientLocationEntity;
import ca.bc.gov.api.oracle.legacy.entity.ClientLocationIdEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientLocationRepository extends
    ReactiveCrudRepository<ClientLocationEntity, ClientLocationIdEntity>,
    ReactiveSortingRepository<ClientLocationEntity, ClientLocationIdEntity>,
    ReactiveQueryByExampleExecutor<ClientLocationEntity> {

  Flux<ClientLocationEntity> findByClientNumber(String clientNumber, Pageable page);

  Mono<ClientLocationEntity> findByClientNumberAndLocationCode(
      String clientNumber,
      String locationCode
  );

  Mono<Long> countByClientNumber(String clientNumber);

}
