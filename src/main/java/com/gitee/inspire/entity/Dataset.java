package com.gitee.inspire.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Dataset {

    private Integer id;

    private String name;

    private Integer planId;

    private Integer parentId;

    private Integer dateType;

    private String dataSource;

    private String content;

    private Date createTime;

    private Date updateTime;

    private List<Dataset> children;

    private List<Rule> rules;
}
