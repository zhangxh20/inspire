package com.github.inspire.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
public class Plan {

    private Integer id;

    private String name;

    private Boolean enable;

    private Integer scheduler;

    private String owner;

    private Date createTime;

    private Date updateTime;

    @TableField(exist = false)
    private List<Dataset> datasets;

}
