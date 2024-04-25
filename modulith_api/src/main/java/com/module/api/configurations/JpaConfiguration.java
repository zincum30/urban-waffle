package com.module.api.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.reactive.TransactionContext;
import org.springframework.transaction.reactive.TransactionSynchronizationManager;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties({JpaProperties.class, HibernateProperties.class})
public class JpaConfiguration {

    private final DataSourceConfiguration dataSourceConfiguration;

    private static final String MAIN_ENTITY_MANAGER_FACTORY = "entityManagerFactory";
    private static final String DORMANT_ENTITY_MANAGER_FACTORY = "dormantEntityManagerFactory";

    private static final String MAIN_TRANSACTION_MANAGER = "mainTransactionManager";
    private static final String DORMANT_TRANSACTION_MANAGER = "dormantTransactionManager";
    //public static final String TRANSACTION_SYNCHRONIZATION_MANAGER = "transactionSynchronizationManager";
    public static final String CHAINED_TRANSACTION_MANAGER = "chainedTransactionManager";

    @Primary
    @Bean(name = MAIN_ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean mainEntityManagerFactory() {

        LocalContainerEntityManagerFactoryBean mainFactoryBean = new LocalContainerEntityManagerFactoryBean();

        mainFactoryBean.setDataSource(dataSourceConfiguration.mainDataSource());
        mainFactoryBean.setPackagesToScan("com.homework.jinsimver.entity.*");
        mainFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return mainFactoryBean;

    }

    @Bean(name = DORMANT_ENTITY_MANAGER_FACTORY)
    public LocalContainerEntityManagerFactoryBean dormantEntityManagerFactory() {

        LocalContainerEntityManagerFactoryBean dormantFactoryBean = new LocalContainerEntityManagerFactoryBean();

        dormantFactoryBean.setDataSource(dataSourceConfiguration.dormantDataSource());
        dormantFactoryBean.setPackagesToScan("com.homework.jinsimver.dormant.entity");
        dormantFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return dormantFactoryBean;

    }

    @Primary
    @Bean(name = MAIN_TRANSACTION_MANAGER)
    public PlatformTransactionManager mainTransactionManager() {
        JpaTransactionManager mainTransactionManager = new JpaTransactionManager();
        mainTransactionManager.setEntityManagerFactory(mainEntityManagerFactory().getObject());
        return mainTransactionManager;
    }


    @Bean(name = DORMANT_TRANSACTION_MANAGER)
    public PlatformTransactionManager dormantTransactionManager() {
        JpaTransactionManager dormantTransactionManager = new JpaTransactionManager();
        dormantTransactionManager.setEntityManagerFactory(dormantEntityManagerFactory().getObject());
        return dormantTransactionManager;
    }

//    @Bean(name = TRANSACTION_SYNCHRONIZATION_MANAGER)
//    public TransactionSynchronizationManager transactionSynchronizationManager(
//            @Qualifier(MAIN_TRANSACTION_MANAGER) PlatformTransactionManager mainTransactionManager,
//            @Qualifier(DORMANT_TRANSACTION_MANAGER) PlatformTransactionManager dormantTransactionManager
//    ) {
//        return new TransactionSynchronizationManager(transactionContext);
//    }


    @Bean(name = CHAINED_TRANSACTION_MANAGER)
    public PlatformTransactionManager chainedTransactionManager(
            @Qualifier(MAIN_TRANSACTION_MANAGER) PlatformTransactionManager mainTransactionManager,
            @Qualifier(DORMANT_TRANSACTION_MANAGER) PlatformTransactionManager dormantTransactionManager
    ) {
        return new ChainedTransactionManager(mainTransactionManager,dormantTransactionManager);
    }




    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.homework.jinsimver.repository.*",
            entityManagerFactoryRef = JpaConfiguration.MAIN_ENTITY_MANAGER_FACTORY,
            transactionManagerRef = JpaConfiguration.MAIN_TRANSACTION_MANAGER
    )
    public static class MainJpaRepositoriesConfig{}


    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.homework.jinsimver.dormant.repository",
            entityManagerFactoryRef = JpaConfiguration.DORMANT_ENTITY_MANAGER_FACTORY,
            transactionManagerRef = JpaConfiguration.DORMANT_TRANSACTION_MANAGER
    )
    public static class DormantJpaRepositoriesConfig{}

}





