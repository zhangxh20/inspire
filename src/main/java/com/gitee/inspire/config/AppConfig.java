package com.gitee.inspire.config;

import com.gitee.inspire.manager.DatasourceManager;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    @Bean
    public DatasourceManager datasourceManager(DatasourceProperties properties) {
        DatasourceManager manager = new DatasourceManager();
        List<ConnectionProperties> connections = properties.getConnections();
        for (ConnectionProperties prop: connections) {
            HikariDataSource dataSource = DataSourceBuilder.create()
                    .username(prop.getUsername())
                    .password(prop.getPassword())
                    .url(prop.getUrl())
                    .driverClassName(DRIVER)
                    .type(HikariDataSource.class)
                    .build();
            dataSource.setMinimumIdle(1);
            dataSource.setIdleTimeout(60000);
            dataSource.setMaximumPoolSize(10);
            manager.addDataSource(prop.getName(), dataSource);
        }
        return manager;
    }
}
