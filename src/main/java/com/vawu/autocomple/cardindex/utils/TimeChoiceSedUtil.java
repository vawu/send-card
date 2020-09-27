package com.vawu.autocomple.cardindex.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@Data
public class TimeChoiceSedUtil {
    String sed;
    Integer hour;
    @PostConstruct
    protected void init() {
        Integer time = TimeUtils.getShanghaiHour();
        if (time >= 0 && time < 10) {
            sed = "早上";
        } else if (time >= 10 && time < 15) {
            sed = "中午";
        } else
            sed = "晚上";
        this.hour = time;
    }
}
