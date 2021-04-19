package com.github.inspire.controller;

import com.github.inspire.dto.Result;
import com.github.inspire.entity.Dataset;
import com.github.inspire.service.DatasetService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("delete")
    public Result<Void> delete(Integer id) {
        boolean delete = datasetService.delete(id);
        if (delete) {
            return Result.success(null);
        } else {
            return Result.fail("删除失败");
        }
    }

    @GetMapping("getDatasets")
    public Result<List<Dataset>> getDatasets(Integer planId) {
        return Result.success(datasetService.getDatasets(planId));
    }
}
