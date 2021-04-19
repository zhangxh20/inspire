package com.github.inspire.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.inspire.dao.DatasetMapper;
import com.github.inspire.dao.RuleMapper;
import com.github.inspire.entity.Dataset;
import com.github.inspire.entity.Rule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

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

    public List<Dataset> getDatasets(Integer planId) {
        List<Dataset> list = new ArrayList<>();
        Dataset dataset = new Dataset();
        dataset.setLevel(0);
        dataset.setName("根节点");
        dataset.setId(0);
        list.add(dataset);
        List<Dataset> datasets = datasetMapper.selectList(Wrappers.<Dataset>lambdaQuery()
                .eq(Dataset::getPlanId, planId)
        );
        list.addAll(datasets);
        return list;
    }
}
