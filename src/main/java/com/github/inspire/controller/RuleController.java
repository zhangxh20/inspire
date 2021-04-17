package com.github.inspire.controller;

import com.github.inspire.dto.Result;
import com.github.inspire.entity.Rule;
import com.github.inspire.service.RuleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("rule")
@RestController
public class RuleController {

    @Resource
    private RuleService ruleService;

    @PostMapping("update")
    public Result<Void> update(@RequestBody Rule rule) {
        ruleService.update(rule);
        return Result.success(null);
    }
}
