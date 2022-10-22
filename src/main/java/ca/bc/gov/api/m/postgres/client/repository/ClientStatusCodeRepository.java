package ca.bc.gov.api.m.postgres.client.repository;

import org.springframework.stereotype.Repository;

import ca.bc.gov.api.core.repository.CoreRepository;
import ca.bc.gov.api.m.postgres.client.entity.ClientStatusCodeEntity;

@Repository
public interface ClientStatusCodeRepository extends CoreRepository<ClientStatusCodeEntity> {
	
}
