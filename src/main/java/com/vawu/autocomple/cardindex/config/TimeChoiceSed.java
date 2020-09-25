package com.vawu.autocomple.cardindex.config;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@Data
public class TimeChoiceSed {
    String sed;

    @PostConstruct
    protected void init() {
        Integer time = DateUtil.thisHour(true);
        if (time >= 0 && time < 11) {
            sed = "早上";
        } else if (time >= 11 && time < 15) {
            sed = "中午";
        } else
            sed = "晚上";
    }
}
