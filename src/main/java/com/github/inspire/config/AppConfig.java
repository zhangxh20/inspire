package com.github.inspire.config;

import com.github.inspire.manager.DatasourceManager;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.List;

@Slf4j
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
            log.info("load datasource:" + prop.getName());
        }
        return manager;
    }

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        int processors = Runtime.getRuntime().availableProcessors();
        executor.setCorePoolSize(processors * 2);
        executor.setMaxPoolSize(processors * 4);
        executor.setThreadNamePrefix("inspire");
        return executor;
    }

    @Bean
    public ThreadPoolTaskScheduler scheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        int processors = Runtime.getRuntime().availableProcessors();
        scheduler.setPoolSize(processors);
        scheduler.setThreadNamePrefix("scheduler");
        return scheduler;
    }
}
