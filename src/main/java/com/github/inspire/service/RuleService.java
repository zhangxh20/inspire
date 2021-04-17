package com.github.inspire.service;

import com.github.inspire.dao.RuleMapper;
import com.github.inspire.entity.Rule;
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
