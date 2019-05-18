package com.example.demo.db;

import com.example.demo.db.annotation.Secondary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableConfigurationProperties({JpaProperties.class, HibernateProperties.class})
@EnableTransactionManagement //启用事物
@EnableJpaRepositories(entityManagerFactoryRef = "secondaryEntityManagerFactory", transactionManagerRef = "secondaryTransactionManager",
        basePackages = {"com.example.demo.repository.secondary"})
public class SecondaryJpaRepositoryConfiguration {
    private final JpaProperties jpaProperties;
    private final HibernateProperties hibernateProperties;
    private final DataSource dataSource;

    @Autowired
    public SecondaryJpaRepositoryConfiguration(JpaProperties jpaProperties, HibernateProperties hibernateProperties, @Secondary DataSource dataSource) {
        this.jpaProperties = jpaProperties;
        this.hibernateProperties = hibernateProperties;
        this.dataSource = dataSource;
    }

    @Secondary
    @Bean
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(dataSource)
                .properties(hibernateProperties.determineHibernateProperties(jpaProperties.getProperties(), new HibernateSettings()))
                .packages("com.example.demo.domain.secondary")
                .persistenceUnit("secondaryPersistenceUnit")
                .build();

//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        factory.setPackagesToScan("com.example.demo.domain.secondary");
//        factory.setPersistenceUnitName("secondaryPersistenceUnit");
//        factory.setDataSource(dataSource);
//        return factory;
    }

    @Secondary
    @Bean
    public PlatformTransactionManager secondaryTransactionManager(@Secondary EntityManagerFactory factory) {
        return new JpaTransactionManager(factory);
    }
}
