package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientPublicViewRepository extends
    JpaRepository<ClientPublicViewEntity, String>,
    QuerydslPredicateExecutor<ClientPublicViewEntity> {
  List<ClientPublicViewEntity> findByClientTypeCodeNot(String clientTypeCode, Pageable pageable);
}
