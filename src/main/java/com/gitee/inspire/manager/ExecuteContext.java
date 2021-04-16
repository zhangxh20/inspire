package com.gitee.inspire.manager;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

@Data
public class ExecuteContext {

    private LongAdder totalCount = new LongAdder();

    public LongAdder successCount = new LongAdder();

    public LongAdder failCount = new LongAdder();

    public List<Map<String, Object>> failList = new ArrayList<>();

    public Boolean running = true;
}
