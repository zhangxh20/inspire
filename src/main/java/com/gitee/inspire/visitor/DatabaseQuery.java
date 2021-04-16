package com.gitee.inspire.visitor;

import lombok.Data;

@Data
public class DatabaseQuery {

    private Integer offset;

    private Integer pageSize;

    private String sql;

    private String datasource;
}
