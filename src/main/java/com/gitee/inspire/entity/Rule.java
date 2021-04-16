package com.gitee.inspire.entity;

import lombok.Data;

@Data
public class Rule {

    private Integer id;

    public String expression;

    public Integer datasetId;

}
