package com.vawu.autocomple.cardindex.beans;

import com.vawu.autocomple.cardindex.condition.TaskCondition;
import com.vawu.autocomple.cardindex.condition.TaskDayCondition;
import com.vawu.autocomple.cardindex.tasks.FixedScheduleTask;
import com.vawu.autocomple.cardindex.tasks.SaticScheduleTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduledBeans {
    @Bean
    @Conditional(TaskCondition.class)
    public SaticScheduleTask getSaticScheduleTask() {
        return new SaticScheduleTask();
    }

    @Bean
    @Conditional(TaskDayCondition.class)
    public FixedScheduleTask getFixedScheduleTask() {
        return new FixedScheduleTask();
    }
}
