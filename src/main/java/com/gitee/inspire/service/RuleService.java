package com.gitee.inspire.service;

import com.gitee.inspire.dao.RuleMapper;
import com.gitee.inspire.entity.Rule;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RuleService {

    @Resource
    private RuleMapper ruleMapper;

    public void update(Rule rule) {
        if (rule.getId() == -1) {
            rule.setId(null);
            ruleMapper.insert(rule);
        } else {
            ruleMapper.updateById(rule);
        }
    }

    public void delete (Integer id) {
        ruleMapper.deleteById(id);
    }
}
