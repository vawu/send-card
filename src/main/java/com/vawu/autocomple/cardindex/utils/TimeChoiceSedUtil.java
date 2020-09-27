package com.vawu.autocomple.cardindex.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@Data
public class TimeChoiceSedUtil {
    String sed;

    public String getSed() {
        Integer time = TimeUtils.getShanghaiHour();
        if (time >= 0 && time < 10) {
            sed = "早上";
        } else if (time >= 10 && time < 15) {
            sed = "中午";
        } else
            sed = "晚上";
        return sed;
    }
}
