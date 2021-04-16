package com.gitee.inspire.service;

import com.gitee.inspire.dao.DatasetMapper;
import com.gitee.inspire.entity.Dataset;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DatasetService {

    @Resource
    private DatasetMapper datasetMapper;

    public void update(Dataset dataset) {

    }
}
