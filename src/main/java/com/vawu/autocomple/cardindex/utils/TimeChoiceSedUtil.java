package com.vawu.autocomple.cardindex.utils;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Configuration
@Data
public class TimeChoiceSedUtil {
    String sed;

    @PostConstruct
    protected void init() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        Integer time = zonedDateTime.getHour();
        if (time >= 0 && time < 10) {
            sed = "早上";
        } else if (time >= 10 && time < 15) {
            sed = "中午";
        } else
            sed = "晚上";
        log.info("hours--->" + time.toString());
    }
}
