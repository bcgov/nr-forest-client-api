package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ClientPublicViewRepository extends
    ReactiveCrudRepository<ClientPublicViewEntity, String>,
    ReactiveSortingRepository<ClientPublicViewEntity, String>,
    ReactiveQueryByExampleExecutor<ClientPublicViewEntity> {
  Flux<ClientPublicViewEntity> findByClientTypeCodeNot(String clientTypeCode, Pageable pageable);
}
