package com.github.inspire.controller;

import com.github.inspire.dto.Result;
import com.github.inspire.entity.Dataset;
import com.github.inspire.service.DatasetService;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("dataset")
@RestController
public class DatasetController {

    @Resource
    private DatasetService datasetService;

    @PostMapping("update")
    public Result<Void> update(@RequestBody Dataset dataset) {
        datasetService.update(dataset);
        return Result.success(null);
    }

    @PostMapping("delete")
    public Result<Void> delete(Integer id) {
        boolean delete = datasetService.delete(id);
        if (delete) {
            return Result.success(null);
        } else {
            return Result.fail("删除失败");
        }
    }
}
