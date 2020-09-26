package com.vawu.autocomple.cardindex.tasks;

import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;
@DependsOn("commonSendTask")
public class SaticScheduleTask extends CommonSendTask{
    //3.添加定时任务
    //或直接指定时间间隔，例如：5秒
    @Scheduled(fixedRate = 5000)
    private void configureTasks() {
        startTask();
    }

}