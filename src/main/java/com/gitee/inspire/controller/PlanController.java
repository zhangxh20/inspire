package com.gitee.inspire.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gitee.inspire.dto.QueryPlanReq;
import com.gitee.inspire.dto.Result;
import com.gitee.inspire.entity.Plan;
import com.gitee.inspire.service.PlanService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("plan")
public class PlanController {


    @Resource
    public PlanService planService;

    @GetMapping("list")
    public Result<IPage<Plan>> list(@RequestBody QueryPlanReq req) {
        return Result.success(planService.query(req));
    }

}
