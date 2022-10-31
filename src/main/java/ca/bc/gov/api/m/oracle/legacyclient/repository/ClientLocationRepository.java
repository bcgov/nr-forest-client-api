package ca.bc.gov.api.m.oracle.legacyclient.repository;

import org.springframework.stereotype.Repository;

import ca.bc.gov.api.core.repository.CoreRepository;
import ca.bc.gov.api.m.oracle.legacyclient.entity.ClientLocationEntity;

@Repository
public interface ClientLocationRepository extends CoreRepository<ClientLocationEntity> {

}
