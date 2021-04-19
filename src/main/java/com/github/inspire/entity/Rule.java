package com.github.inspire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class Rule {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String expression;

    private Integer datasetId;

    private Date createTime;

    private Date updateTime;
}
