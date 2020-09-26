package com.vawu.autocomple.cardindex.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;
@Configuration
public class TaskCondition implements Condition {

    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        return !"666".equals(conditionContext.getEnvironment().getProperty("stu.timeTemp"));
    }
}
