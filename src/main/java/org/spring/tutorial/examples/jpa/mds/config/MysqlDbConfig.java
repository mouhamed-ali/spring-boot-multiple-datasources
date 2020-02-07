package org.spring.tutorial.examples.jpa.mds.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        basePackages = {"org.spring.tutorial.examples.jpa.mds.repository.mysql"}
)
/*
 * in this class we gonna configure the mysql to use the default factory 'entityManagerFactory'
 */
public class MysqlDbConfig {

    @Primary
    /*
     * Indicates that a bean should be given preference when multiple candidates are qualified to autowire a
     * single-valued dependency. If exactly one 'primary' bean exists among the candidates, it will be the autowired value.
     * spring will throw an exception because we have another DataSource bean (the bean type is DataSource)
     */
    @Bean
    /*
     * the bean name is dataSource -> the method name
     */
    @ConfigurationProperties(prefix = "mysql.datasource")
    /*
     * the Mysql properties
     */
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {

        Map<String,String> hibernateProperties = new HashMap<>();
        hibernateProperties.put("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.put("hibernate.show_sql", "true");
        hibernateProperties.put("hibernate.format_sql", "true");
        hibernateProperties.put("hibernate.hbm2ddl.import_files", "initial-db-mysql.sql");

        return builder
                .dataSource(dataSource())
                .packages("org.spring.tutorial.examples.jpa.mds.entity.mysql")
                .properties(hibernateProperties)
                .persistenceUnit("mysqlPU")
                .build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory){
        return new JpaTransactionManager(entityManagerFactory);
    }
}
