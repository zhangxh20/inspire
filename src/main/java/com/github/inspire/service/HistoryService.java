package com.github.inspire.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.inspire.dao.HistoryMapper;

import com.github.inspire.entity.History;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HistoryService {

    @Resource
    public HistoryMapper resultMapper;

    public Page<History> query(Integer planId, Integer page, Integer size) {
        return resultMapper.selectPage(new Page<>(page, size), Wrappers.<History> lambdaQuery()
                    .eq(History::getPlanId, planId)
        );
    }
}
