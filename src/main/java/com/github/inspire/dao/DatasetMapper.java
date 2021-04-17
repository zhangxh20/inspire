package com.github.inspire.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.inspire.entity.Dataset;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DatasetMapper extends BaseMapper<Dataset> {
}
