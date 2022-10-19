package ca.bc.gov.app.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

@NoRepositoryBean
@Transactional(value = "transactionManager")
public interface CoreRepository<E> extends JpaRepository<E, Serializable>, PagingAndSortingRepository<E, Serializable>, JpaSpecificationExecutor<E> {
	
}
