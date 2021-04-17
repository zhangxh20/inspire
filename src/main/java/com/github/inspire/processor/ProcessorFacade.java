package com.github.inspire.processor;

import com.github.inspire.dao.ResultMapper;
import com.github.inspire.entity.Dataset;
import com.github.inspire.entity.Plan;
import com.github.inspire.entity.Result;
import com.github.inspire.manager.ExecuteContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ProcessorFacade {

    @Resource
    private List<Processor> processors;
    @Resource
    private ResultMapper resultMapper;

    private final Map<Integer, Processor> processorMap = new HashMap<>();

    private final Map<Integer, ExecuteContext> contextMap = new ConcurrentHashMap<>();

    @PostConstruct
    private void init() {
        processors.forEach(p -> {
            processorMap.put(p.getType().getCode(), p);
        });
    }

    public Processor getProcessor(Integer dataType) {
        return processorMap.get(dataType);
    }

    public void execute(Plan plan) {
        StopWatch stopWatch =  new StopWatch();
        stopWatch.start();
        Result result = record(plan);
        ExecuteContext context = new ExecuteContext();
        contextMap.put(plan.getId(), context);
        try {
            executeDatasets(plan.getDatasets(), context);
            result.setSuccessCount(context.getSuccessCount().intValue());
            result.setFailCount(context.getFailCount().intValue());
            result.setTotalCount(context.getTotalCount().intValue());
            if (context.getRunning()) {
                result.setStatus(result.getFailCount() > 0 ? "fail" : "success");
            } else {
                result.setStatus("suspend");
            }
            log.info("执行结果,result:{}", result);
        } catch (Exception e) {
            log.error("execute error", e);
        } finally {
            stopWatch.stop();
            result.setTimeCost((int)stopWatch.getLastTaskTimeMillis());
            updateResult(result);
            contextMap.remove(plan.getId());
        }
    }

    private void executeDatasets(List<Dataset> datasets, ExecuteContext context) throws Exception {
        Map<String, Object> parent = new HashMap<>();
        for (Dataset dataset : datasets) {
            Processor processor = getProcessor(dataset.getDateType());
            processor.process(context, dataset, parent, true);
        }
    }

    private Result record(Plan plan) {
        Result result = new Result();
        result.setPlanId(plan.getId());
        result.setPlanName(plan.getName());
        resultMapper.insert(result);
        return result;
    }

    private void updateResult(Result result) {
        resultMapper.updateById(result);
    }

    public void stop(Integer planId) {
        ExecuteContext context = contextMap.get(planId);
        if (context != null) {
            context.setRunning(false);
        }
    }

}
