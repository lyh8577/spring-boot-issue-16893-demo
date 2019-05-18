package com.example.demo.db;

import com.example.demo.db.annotation.Secondary;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Secondary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSourceProperties secondaryDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean
    public DataSource primaryDataSource(DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Secondary
    @Bean
    public DataSource secondaryDataSource(@Secondary DataSourceProperties properties) {
        return properties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Primary
    @Bean
    public JdbcTemplate primaryJdbcTemplate(DataSource source) {
        return new JdbcTemplate(source);
    }

    @Secondary
    @Bean
    public JdbcTemplate secondaryJdbcTemplate(@Secondary DataSource source) {
        return new JdbcTemplate(source);
    }
}
