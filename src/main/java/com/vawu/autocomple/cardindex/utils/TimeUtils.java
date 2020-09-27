package com.vawu.autocomple.cardindex.utils;

import cn.hutool.core.date.DateUtil;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class TimeUtils {
    private static ZonedDateTime zonedDateTime;


    public static String getFormatedDate() {
        zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        return DateUtil.format(zonedDateTime.toLocalDateTime(), "yyyy-MM-dd");
    }

    public static Integer getShanghaiHour() {
        zonedDateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        return zonedDateTime.toLocalDateTime().getHour();
    }
}
