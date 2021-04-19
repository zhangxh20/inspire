package com.github.inspire.processor;

import com.github.inspire.common.enums.DataType;
import com.github.inspire.entity.Dataset;
import com.github.inspire.manager.ExecuteContext;
import com.github.inspire.manager.RuleEngine;
import com.github.inspire.visitor.DatabaseQuery;
import com.github.inspire.visitor.DatabaseVisitor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class DatabaseProcessor implements Processor{

    private static final int PAGE_SIZE = 10000;

    @Resource
    private DatabaseVisitor visitor;

    @Resource
    private RuleEngine ruleEngine;

    @Resource
    private ProcessorFacade facade;

    @Override
    public void process(ExecuteContext context, Dataset dataset, Map<String, Object> parent, boolean joinAble) throws Exception{
        DatabaseQuery query = new DatabaseQuery();
        query.setSql(dataset.getContent());
        query.setDatasource(dataset.getDatasource());
        final int count = visitor.count(query, parent);
        if (count == 0) {
            ruleEngine.execute(parent, dataset.getRules(), context);
            return;
        }
        int pages = 1;
        int pageSize = count;
        int offset = 0;
        if (count > 100000) {
            pages = (count + PAGE_SIZE - 1) / PAGE_SIZE;
            pageSize = PAGE_SIZE;
        }
        for (int i = 0; i < pages; i++) {
            if (!context.getRunning()) {
                break;
            }
            query.setOffset(offset);
            query.setPageSize(pageSize);
            List<Map<String, Object>> dataList = visitor.selectList(query, parent);
            Stream<Map<String, Object>> stream;
            if (joinAble) {
                stream = dataList.parallelStream();
            } else {
                stream = dataList.stream();
            }
            stream.forEach(d -> {
                if (!context.getRunning()) {
                    return;
                }
                d.putAll(parent);
                ruleEngine.execute(d, dataset.getRules(), context);
                for (Dataset child : dataset.getChildren()) {
                    Processor processor = facade.getProcessor(child.getDataType());
                    try {
                        processor.process(context, child, d, false);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            });

        }
    }

    @Override
    public DataType getType() {
        return DataType.DATABASE;
    }
}
