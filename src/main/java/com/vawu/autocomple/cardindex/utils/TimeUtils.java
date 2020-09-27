package com.vawu.autocomple.cardindex.utils;

import cn.hutool.core.date.DateUtil;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
    private static ZonedDateTime zonedDateTime;
    private static LocalDateTime time;

    static {
        zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        time = zonedDateTime.toLocalDateTime();
    }

    public static String getFormatedDate() {
        return DateUtil.format(time, "yyyy-MM-dd");
    }

    public static Integer getShanghaiHour() {
        return time.getHour();
    }
}
