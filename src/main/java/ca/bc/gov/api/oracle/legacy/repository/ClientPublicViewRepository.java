package ca.bc.gov.api.oracle.legacy.repository;

import ca.bc.gov.api.oracle.legacy.entity.ClientPublicViewEntity;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
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


  @Query("""
      SELECT * FROM THE.V_CLIENT_PUBLIC
      WHERE
       (CLIENT_NAME = :clientName OR CLIENT_NAME IS NULL) AND
       (LEGAL_FIRST_NAME = :firstName OR LEGAL_FIRST_NAME IS NULL) AND
       (LEGAL_MIDDLE_NAME = :middleName OR LEGAL_MIDDLE_NAME IS NULL) AND
       (CLIENT_TYPE_CODE IN (:clientTypes) OR CLIENT_TYPE_CODE IS NOT NULL)""")
  Flux<ClientPublicViewEntity> searchByNames(
      @Param("clientName") String clientName,
      @Param("firstName") String firstName,
      @Param("middleName") String middleName,
      @Param("clientTypes") List<String> clientTypes,
      Pageable pageable
  );

}
