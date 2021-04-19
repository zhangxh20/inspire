package com.github.inspire.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.inspire.dto.Result;
import com.github.inspire.entity.History;
import com.github.inspire.service.HistoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController("history")
public class HistoryController {

    @Resource
    private HistoryService historyService;

    @GetMapping("query")
    public Result<IPage<History>> query(Integer planId, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return Result.success(historyService.query(planId, page, size));
    }
}
