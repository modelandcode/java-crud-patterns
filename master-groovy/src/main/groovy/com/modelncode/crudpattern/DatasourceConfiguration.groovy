package com.modelncode.crudpattern

import groovy.sql.Sql
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy
import org.springframework.transaction.annotation.EnableTransactionManagement

import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
class DatasourceConfiguration {
	
	@Primary
    @Bean
	@ConfigurationProperties(prefix = "spring.datasource") 
    DataSource dataSource() {
        return DataSourceBuilder.create().build()
    }

//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//        return new JdbcTemplate(dataSource())
//    }

    @Bean
    TransactionAwareDataSourceProxy dataSourceProxy() {
        return new TransactionAwareDataSourceProxy(dataSource())
    }

    @Bean
    DataSourceTransactionManager transactionManager() {
        final DataSourceTransactionManager transactionManager =
                new DataSourceTransactionManager(dataSourceProxy())
        return transactionManager
    }

    @Bean
    Sql sql() {
        return new Sql(dataSourceProxy())
    }
} 
