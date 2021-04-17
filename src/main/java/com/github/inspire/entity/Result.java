package com.github.inspire.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Result {

    private Integer id;

    private Integer planId;

    private String planName;

    private Integer totalCount;

    private Integer successCount;

    private Integer failCount;

    private Integer timeCost;

    private String status;

    private Date createTime;

    private Date updateTime;
}
