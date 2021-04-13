package com.gitee.inspire.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.inspire.dao.PlanMapper;
import com.gitee.inspire.dto.QueryPlanReq;
import com.gitee.inspire.entity.Plan;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class PlanService {

    @Resource
    public PlanMapper planMapper;

    public Page<Plan> query(QueryPlanReq req) {
        return planMapper.selectPage(new Page<>(req.getPage(),req.getSize()), Wrappers.<Plan>lambdaQuery()
                .eq(StringUtils.hasLength(req.getName()), Plan::getName, req.getName())
        );
    }
}
