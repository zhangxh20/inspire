package com.gitee.inspire.processor;

import org.springframework.stereotype.Component;

@Component
public class DatabaseProcessor implements Processor{
    @Override
    public Integer getType() {
        return 1;
    }
}
