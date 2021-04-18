package com.github.inspire.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.inspire.dao.DatasetMapper;
import com.github.inspire.dao.RuleMapper;
import com.github.inspire.entity.Dataset;
import com.github.inspire.entity.Rule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DatasetService {

    @Resource
    private DatasetMapper datasetMapper;
    @Resource
    private RuleMapper ruleMapper;

    public void update(Dataset dataset) {
        if (dataset.getId() == -1) {
            dataset.setId(null);
            datasetMapper.insert(dataset);
        } else {
            datasetMapper.updateById(dataset);
        }
    }

    public boolean delete(Integer id) {
        Integer count = datasetMapper.selectCount(Wrappers.<Dataset>lambdaQuery()
                .eq(Dataset::getParentId, id)
        );
        if (count > 0 ) {
            return false;
        }
        ruleMapper.delete(Wrappers.<Rule> lambdaQuery()
                .eq(Rule::getDatasetId, id)
        );
        datasetMapper.deleteById(id);
        return true;
    }
}
