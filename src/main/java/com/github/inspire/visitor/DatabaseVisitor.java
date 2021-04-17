package com.github.inspire.visitor;

import com.github.inspire.manager.DatasourceManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Component
public class DatabaseVisitor {

    @Resource
    private DatasourceManager datasourceManager;

    public int count(DatabaseQuery query, Map<String, Object> params) throws SQLException {
        String sql = "select count(1) totalCount from (" + setParams(query.getSql(), params) + ") tb";
        Map<String, Object> result = getQueryRunner(query.getDatasource()).query(sql, new MapHandler());
        Long count = (Long) result.get("totalCount");
        return count.intValue();
    }

    public List<Map<String, Object>> selectList(DatabaseQuery query, Map<String, Object> params) throws SQLException {
        String sql = setParams(query.getSql(), params) + " limit " + query.getOffset() + "," + query.getPageSize();
        return getQueryRunner(query.getDatasource()).query(sql, new MapListHandler());
    }

    private String  setParams(String sql, Map<String, Object> params) {
        Set<String> fields = params.keySet();
        for (String fieldName : fields) {
            Object obj = params.get(fieldName);
            String value;
            if (obj instanceof Number) {
                value = obj.toString();
            } else {
                value = "'" + obj.toString() +"'";
            }
            sql = sql.replace("#{" + fieldName + "}", value);
        }
        return sql;
    }

    private QueryRunner getQueryRunner(String database) {
        DataSource dataSource = datasourceManager.getDataSource(database);
        if (dataSource == null) {
            throw new RuntimeException("sdf");
        }
        return new QueryRunner(dataSource);
    }

}
