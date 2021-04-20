package com.github.inspire.scheduler;

import com.github.inspire.common.enums.SchedulerType;
import com.github.inspire.service.PlanService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ExecuteTaskScheduler {

    @Resource
    private PlanService planService;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void halfHour() {
        planService.execute(SchedulerType.HALF_HOUR);
    }

    @Scheduled(cron = "0 1 * * * ?")
    public void oneHour() {
        planService.execute(SchedulerType.ONE_HOUR);
    }

    @Scheduled(cron = "0 5 0/4 * * ?")
    public void fourHour() {
        planService.execute(SchedulerType.FOUR_HOUR);
    }

    @Scheduled(cron = "0 10 0 * * ?")
    public void oneDay() {
        planService.execute(SchedulerType.ONE_DAY);
    }
}
