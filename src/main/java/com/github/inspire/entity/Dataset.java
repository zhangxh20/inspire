package com.github.inspire.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Dataset {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private Integer planId;

    private Integer parentId;

    private Integer dataType;

    private String datasource;

    private String content;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private Integer level;

    @TableField(exist = false)
    private List<Dataset> children;

    @TableField(exist = false)
    private List<Rule> rules;
}
