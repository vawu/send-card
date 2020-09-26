package com.vawu.autocomple.cardindex.tasks;

import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;

@DependsOn("commonSendTask")
public class FixedScheduleTask extends CommonSendTask {

    @Scheduled(cron = "0 0 6,9,21 * * ?")
    private void configureDayTasks() {
        startTask();
    }

}
