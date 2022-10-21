package ca.bc.gov.api.mpg.clientstatuscode.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ca.bc.gov.api.core.repository.CoreRepository;
import ca.bc.gov.api.mpg.clientstatuscode.entity.ClientStatusCodeEntity;

@Repository
public interface ClientStatusCodeRepository extends CoreRepository<ClientStatusCodeEntity> {
}
