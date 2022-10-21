package ca.bc.gov.api.core.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
  entityManagerFactoryRef = "postgresEntityMgrFactory", 
  // transactionManagerRef = "postgresTransactionMgr", 
  basePackages = "ca.bc.gov.api.mpg")
// @EnableTransactionManagement
public class PostgresDBConfig {

    @Bean(name = "postgresdb")
    @ConfigurationProperties(prefix = "spring.pgdatasource")
    @Primary
    public DataSource dataSource() {
      return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresEntityMgrFactory")
    @Primary
    public LocalContainerEntityManagerFactoryBean postgresEntityMgrFactory(
      final EntityManagerFactoryBuilder builder,
      @Qualifier("postgresdb") final DataSource dataSource) {
      return builder
        .dataSource(dataSource)
        .packages("ca.bc.gov.api.mpg")
        .persistenceUnit("postgres")
        .build();
    }

    // @Bean(name = "postgresTransactionMgr")
    // @Primary
    // public PlatformTransactionManager postgresTransactionMgr(
    //   @Qualifier("postgresEntityMgrFactory") final EntityManagerFactory entityManagerFactory) {
    //   return new JpaTransactionManager(entityManagerFactory);
    // }
}