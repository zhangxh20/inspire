package com.github.inspire.processor;

import com.github.inspire.common.enums.DataType;
import com.github.inspire.entity.Dataset;
import com.github.inspire.manager.ExecuteContext;

import java.util.Map;

public interface Processor {

    void process(ExecuteContext context, Dataset dataset, Map<String, Object> parent, boolean joinAble) throws Exception;

    DataType getType();
}
