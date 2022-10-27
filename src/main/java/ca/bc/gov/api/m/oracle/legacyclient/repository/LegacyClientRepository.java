package ca.bc.gov.api.m.oracle.legacyclient.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ca.bc.gov.api.core.repository.CoreRepository;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientPublicViewEntity;

@Repository
public interface LegacyClientRepository extends CoreRepository<ClientPublicViewEntity> {
	
	@Query("select x from ClientPublicViewEntity x " +
	       "where x.clientNumber = :clientNumber")
	ClientPublicViewEntity findByClientNumber(@Param("clientNumber") String clientNumber);

	@Query(value = "select * from V_CLIENT_PUBLIC WHERE CLIENT_TYPE_CODE != 'I'", 
		   countQuery = "select count(*) from V_CLIENT_PUBLIC WHERE CLIENT_TYPE_CODE != 'I'", 
		   nativeQuery = true)
	Page<ClientPublicViewEntity> findAllNonIndividualClients(Pageable paging);

	@Query("select x from ClientPublicViewEntity x " +
	       "where x.clientTypeCode = 'B' and x.clientStatusCode = 'ACT'")
	List<ClientPublicViewEntity> findAllFirstNationBandClients();

}
