package ca.bc.gov.api.core.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
@Transactional(value = "transactionManager")
public interface CoreRepository<E> extends JpaRepository<E, Serializable>, JpaSpecificationExecutor<E> {
	
}
