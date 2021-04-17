package com.github.inspire.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.inspire.dto.QueryPlanReq;
import com.github.inspire.dto.Result;
import com.github.inspire.entity.Plan;
import com.github.inspire.service.PlanService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("plan")
public class PlanController {


    @Resource
    public PlanService planService;

    @PostMapping("query")
    public Result<IPage<Plan>> query(@RequestBody QueryPlanReq req) {
        return Result.success(planService.query(req));
    }

    @RequestMapping
    public Result<Void> execute(Integer planId) {
        return Result.success(null);
    }

}
