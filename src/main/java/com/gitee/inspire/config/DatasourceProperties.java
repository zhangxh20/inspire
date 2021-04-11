package com.gitee.inspire.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "datasource.config")
@Data
public class DatasourceProperties {

    private List<ConnectionProperties>  connections;
}
