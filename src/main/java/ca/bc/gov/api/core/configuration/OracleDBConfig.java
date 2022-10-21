package ca.bc.gov.api.core.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  entityManagerFactoryRef = "oracleEntityMgrFactory", 
  // transactionManagerRef = "oracleTransactionMgr", 
  basePackages = "ca.bc.gov.api.m")
// @EnableTransactionManagement
public class OracleDBConfig {

    @Bean(name = "oracledb")
    @ConfigurationProperties(prefix = "spring.oradatasource")
    public DataSource dataSource() {
      return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleEntityMgrFactory")
    public LocalContainerEntityManagerFactoryBean oracleEntityMgrFactory(
      final EntityManagerFactoryBuilder builder,
      @Qualifier("oracledb") final DataSource dataSource) {
      return builder
        .dataSource(dataSource)
        .packages("ca.bc.gov.api.m")
        .persistenceUnit("oracle")
        .build();
    }

//     @Bean(name = "oracleTransactionMgr")
//     public PlatformTransactionManager oracleTransactionMgr(
//       @Qualifier("oracleEntityMgrFactory") final EntityManagerFactory entityManagerFactory) {
//       return new JpaTransactionManager(entityManagerFactory);
//     }
}