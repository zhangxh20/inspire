package com.github.inspire.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.inspire.dao.DatasetMapper;
import com.github.inspire.dao.PlanMapper;
import com.github.inspire.dao.RuleMapper;
import com.github.inspire.dto.QueryPlanReq;
import com.github.inspire.entity.Dataset;
import com.github.inspire.entity.Plan;
import com.github.inspire.entity.Rule;
import com.github.inspire.processor.ProcessorFacade;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

    @Resource
    public PlanMapper planMapper;
    @Resource
    public DatasetMapper datasetMapper;
    @Resource
    private RuleMapper ruleMapper;
    @Resource
    private ThreadPoolTaskExecutor executor;
    @Resource
    private ProcessorFacade facade;

    public Page<Plan> query(QueryPlanReq req) {
        return planMapper.selectPage(new Page<>(req.getPage(),req.getSize()), Wrappers.<Plan>lambdaQuery()
                .eq(StringUtils.hasLength(req.getName()), Plan::getName, req.getName())
        );
    }

    public void execute(Integer planId) {
        Plan plan = getPlan(planId);
        if (plan == null || !plan.getEnable()) {
            return;
        }
        executor.execute(() -> {
            facade.execute(plan);
        });
    }

    private Plan getPlan(Integer planId) {
        Plan plan = planMapper.selectById(planId);
        if (plan == null) {
            return null;
        }
        List<Dataset> datasets = datasetMapper.selectList(Wrappers.<Dataset> lambdaQuery()
                .eq(Dataset::getPlanId, planId)
                .eq(Dataset::getParentId, 0)
        );
        plan.setDatasets(datasets);
        for (Dataset dataset : datasets) {
            recurse(dataset);
        }
        return plan;
    }

    private void recurse(Dataset dataset) {
        List<Rule> rules = ruleMapper.selectList(Wrappers.<Rule>lambdaQuery()
                .eq(Rule::getDatasetId, dataset.getId())
        );
        dataset.setRules(rules);
        List<Dataset> children = datasetMapper.selectList(Wrappers.<Dataset>lambdaQuery()
                .eq(Dataset::getParentId, dataset.getId())
        );
        dataset.setChildren(children);
        for (Dataset ds: children) {
            recurse(ds);
        }
    }

    public void stop(Integer planId) {
        facade.stop(planId);
    }

    public Plan getPlanView(Integer id) {
        Plan plan = getPlan(id);
        List<Dataset> datasets = plan.getDatasets();
        List<Dataset> viewDatasets = new ArrayList<>();
        plan.setDatasets(viewDatasets);
        for (Dataset dataset : datasets) {
            dataset.setLevel(0);
            viewDatasets.add(dataset);
            setChildLevel(dataset, viewDatasets);
        }
        return plan;
    }

    private void setChildLevel(Dataset dataset, List<Dataset> list) {
        for (Dataset ds : dataset.getChildren()) {
            ds.setLevel(dataset.getLevel() + 1);
            list.add(ds);
            setChildLevel(ds, list);
        }
    }
}
