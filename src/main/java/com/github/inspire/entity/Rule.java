package com.github.inspire.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Rule {

    private Integer id;

    private String expression;

    private Integer datasetId;

    private Date createTime;

    private Date updateTime;
}
