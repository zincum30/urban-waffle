package com.module.batch.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfiguration {

    private static final String MAIN_PROPERTIES = "spring.datasource.main.hikari";
    private static final String DORMANT_PROPERTIES = "spring.datasource.dormant.hikari";

    public static final String MAIN_DATASOURCE = "mainDataSource";
    public static final String DORMANT_DATASOURCE = "dormantDataSource";


    @Bean
    @ConfigurationProperties(prefix = MAIN_PROPERTIES)
    public HikariConfig mainHikariConfig() {
        return new HikariConfig();
    }


    @Primary
    @Bean(name = MAIN_DATASOURCE)
    public DataSource mainDataSource() {
        return new LazyConnectionDataSourceProxy(new HikariDataSource(mainHikariConfig()));
    }

    @Bean
    @ConfigurationProperties(prefix = DORMANT_PROPERTIES)
    public HikariConfig dormantHikariConfig() {
        return new HikariConfig();
    }

    @Bean(name = DORMANT_DATASOURCE)
    public DataSource dormantDataSource() {
        return new LazyConnectionDataSourceProxy((new HikariDataSource(dormantHikariConfig())));
    }



}
