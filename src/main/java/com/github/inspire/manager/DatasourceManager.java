package com.github.inspire.manager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DatasourceManager {

    private Map<String, DataSource> map = new HashMap<>();

    public void addDataSource(String database, DataSource dataSource) {
        map.put(database, dataSource);
    }

    public DataSource getDataSource(String database) {
        return map.get(database);
    }
}
