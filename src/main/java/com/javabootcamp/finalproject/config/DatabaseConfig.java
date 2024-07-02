package com.javabootcamp.finalproject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@Configuration
public class DatabaseConfig {

    @Bean
    @Lazy
    public DataSource dataSource(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {

        return DataSourceBuilder
                .create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver")
                .build();
    }
}
