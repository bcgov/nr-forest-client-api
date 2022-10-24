package ca.bc.gov.api.core.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "oracleEntityManager", 
					   basePackages = "ca.bc.gov.api.m.oracle")
public class OraclePersistenceConfiguration {

    public final static String ORACLE_ATTRIBUTE_SCHEMA_QUALIFIER = "THE.";
    public final static String ORACLE_ATTRIBUTE_SCHEMA = "THE";
    public static final String ORACLE_API_TAG = "Legacy Client";
    
    @Bean(name = "oracleDataSource")
    @ConfigurationProperties(prefix = "oracle.datasource")
    public DataSource dataSource() {
      return DataSourceBuilder.create().build();
    }

    @Bean(name = "oracleEntityManager")
    public LocalContainerEntityManagerFactoryBean oracleEntityManager(final EntityManagerFactoryBuilder builder,
    																  @Qualifier("oracleDataSource") final DataSource dataSource) {
		return builder.dataSource(dataSource)
					  .packages("ca.bc.gov.api.m.oracle")
					  .persistenceUnit("oracle")
					  .build();
    }

}