package com.github.inspire.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
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

    @PostMapping("create")
    public Result<Void> create(@RequestBody Plan plan) {
        planService.create(plan);
        return Result.success(null);
    }

    @PostMapping("query")
    public Result<IPage<Plan>> query(String name, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "20") Integer size) {
        return Result.success(planService.query(name, page, size));
    }

    @RequestMapping("execute")
    public Result<Void> execute(Integer id) {
        planService.execute(id);
        return Result.success(null);
    }

    @RequestMapping("stop")
    public Result<Void> stop(Integer id) {
        planService.stop(id);
        return Result.success(null);
    }

    @RequestMapping("get")
    public Result<Plan> getPlan(Integer id) {
        return Result.success(planService.getPlanView(id));
    }

}
