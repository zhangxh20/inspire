package com.github.inspire.service;

import com.github.inspire.dao.DatasetMapper;
import com.github.inspire.entity.Dataset;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DatasetService {

    @Resource
    private DatasetMapper datasetMapper;

    public void update(Dataset dataset) {

    }
}
