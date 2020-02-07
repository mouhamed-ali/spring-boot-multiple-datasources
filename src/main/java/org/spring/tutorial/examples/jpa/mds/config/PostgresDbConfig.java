package org.spring.tutorial.examples.jpa.mds.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "postgresEntityManagerFactory",
        transactionManagerRef = "postgresTransactionManager",
        basePackages = {"org.spring.tutorial.examples.jpa.mds.repository.postgres"}
)
/*
 * in this class we gonna configure a new entityManagerFactory for the postgres datasource
 */
public class PostgresDbConfig {

    @Bean(name = "postgresDataSource")
    @ConfigurationProperties(prefix = "postgres.datasource")
    /*
     * the Postgres properties
     */
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

        Map<String, String> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        hibernateProperties.put("hibernate.show_sql","true");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.import_files", "initial-db-postgres.sql");

        return builder
                .dataSource(dataSource())
                .packages("org.spring.tutorial.examples.jpa.mds.entity.postgres")
                .properties(hibernateProperties)
                .persistenceUnit("postgresPU")
                .build();
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager transactionManager(@Qualifier("postgresEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
