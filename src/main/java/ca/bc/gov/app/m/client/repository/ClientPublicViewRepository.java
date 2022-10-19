package ca.bc.gov.app.m.client.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.bc.gov.app.core.repository.CoreRepository;
import ca.bc.gov.app.m.client.entity.ClientPublicViewEntity;

@Repository
public interface ClientPublicViewRepository extends CoreRepository<ClientPublicViewEntity> {

	//TODO: Add annotations and improve logic 
	
	@Query(value = "SELECT * FROM V_CLIENT_PUBLIC WHERE CLIENT_NUMBER = :clientNumber",
		   nativeQuery = true)
	List<ClientPublicViewEntity> findByClientNumber(String clientNumber);

	@Query(value = "SELECT * FROM V_CLIENT_PUBLIC WHERE CLIENT_TYPE_CODE!='I'", 
		   countQuery = "SELECT count(*) FROM V_CLIENT_PUBLIC WHERE CLIENT_TYPE_CODE!='I'", 
		   nativeQuery = true)
	Page<ClientPublicViewEntity> findByClientTypeCodeNotI(Pageable paging);

}
