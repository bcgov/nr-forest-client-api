package ca.bc.gov.api.m.postgres.client.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.bc.gov.api.core.repository.CoreRepository;
import ca.bc.gov.api.m.postgres.client.entity.ClientStatusCodeEntity;

@Repository
public interface ClientStatusCodeRepository extends CoreRepository<ClientStatusCodeEntity> {
	
    @Query("from ClientStatusCodeEntity " +
           "where (expiryDate is null or expiryDate > :activeDate) " +
           "and effectiveDate <= :activeDate")
    List<ClientStatusCodeEntity> findActiveAt(@Param("activeDate") Date activeDate);
    
}
