package com.gitee.inspire.processor;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProcessorFacade {

    @Resource
    private List<Processor> processors;

    private final Map<Integer, Processor> processorMap = new HashMap<>();

    @PostConstruct
    private void init() {
        processors.forEach(p -> {
            processorMap.put(p.getType(), p);
        });
    }

}
