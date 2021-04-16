package com.gitee.inspire.processor;

import com.gitee.inspire.common.enums.DataType;
import com.gitee.inspire.entity.Dataset;
import com.gitee.inspire.manager.ExecuteContext;

import java.util.Map;

public interface Processor {

    void process(ExecuteContext context, Dataset dataset, Map<String, Object> parent, boolean joinAble) throws Exception;

    DataType getType();
}
