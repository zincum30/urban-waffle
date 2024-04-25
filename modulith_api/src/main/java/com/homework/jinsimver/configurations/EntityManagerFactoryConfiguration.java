
//package com.module.api.configurations;
//
//import lombok.Builder;
//import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
//import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
//import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
//import org.springframework.boot.jdbc.metadata.CompositeDataSourcePoolMetadataProvider;
//import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadata;
//import org.springframework.boot.jdbc.metadata.DataSourcePoolMetadataProvider;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.util.ObjectUtils;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//import java.util.Collection;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class EntityManagerFactoryConfiguration {
//
//    private static final String PROVIDER_DISABLES_AUTOCOMMIT = "hibernate.connection.provider_disables_autocommit";
//
//    private final JpaProperties properties;
//    private final HibernateProperties hibernateProperties;
//    private final ObjectProvider<Collection<DataSourcePoolMetadataProvider>> metadataProviders;
//    private final EntityManagerFactoryBuilder entityManagerFactoryBuilder;
//    private final DataSource dataSource;
//    private final String packages;
//    private final String persistenceUnit;
//
//
//    @Builder
//    public EntityManagerFactoryConfiguration(JpaProperties properties, HibernateProperties hibernateProperties, ObjectProvider<Collection<DataSourcePoolMetadataProvider>> metadataProviders, EntityManagerFactoryBuilder entityManagerFactoryBuilder, DataSource dataSource, String packages, String persistenceUnit) {
//        this.properties = properties;
//        this.hibernateProperties = hibernateProperties;
//        this.metadataProviders = metadataProviders;
//        this.entityManagerFactoryBuilder = entityManagerFactoryBuilder;
//        this.dataSource = dataSource;
//        this.packages = packages;
//        this.persistenceUnit = persistenceUnit;
//    }
//
//    public LocalContainerEntityManagerFactoryBean create() {
//        Map<String, Object> vendorProperties = getVendorProperties();
//        customizeVendorProperties(vendorProperties);
//        return (LocalContainerEntityManagerFactoryBean) entityManagerFactoryBuilder.build();
//    }
//
//
//    private String[] getMappingResources() {
//        List<String> mappingResources = this.properties.getMappingResources();
//        return (!ObjectUtils.isEmpty(mappingResources) ? StringUtils.toStringArray(mappingResources) : null);
//    }
//
//    private Map<String, Object> getVendorProperties() {
//        return new LinkedHashMap<>(this.hibernateProperties.determineHibernateProperties(
//                this.properties.getProperties(),
//                new HibernateSettings()));
//    }
//
//    private void customizeVendorProperties(Map<String, Object> vendorProperties) {
//        if (!vendorProperties.containsKey(PROVIDER_DISABLES_AUTOCOMMIT)) {
//            configureProviderDisablesAutocommit(vendorProperties);
//        }
//    }
//
//
//    private void configureProviderDisablesAutocommit(Map<String, Object> vendorProperties) {
//        if (isDataSourceAutoCommitDisabled()) {
//            vendorProperties.put(PROVIDER_DISABLES_AUTOCOMMIT, "true");
//        }
//    }
//
//    private boolean isDataSourceAutoCommitDisabled() {
//        DataSourcePoolMetadataProvider poolMetadataProvider = new CompositeDataSourcePoolMetadataProvider(metadataProviders.getIfAvailable());
//        DataSourcePoolMetadata poolMetadata = poolMetadataProvider.getDataSourcePoolMetadata(this.dataSource);
//        return poolMetadata != null && Boolean.FALSE.equals(poolMetadata.getDefaultAutoCommit());
//    }
//
//
//}
