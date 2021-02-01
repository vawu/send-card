package com.vawu.autocomple.cardindex.tasks;

import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@DependsOn("commonSendTask")
public class FixedScheduleTask extends CommonSendTask {

    @Scheduled(cron = "0 0 10-13 * * ?")
    private void configureDayTasks() {
        HttpResponse response = startTask();
        String body = response.body();
        if (JSONUtil.isJson(body)&& !JSONUtil.isNull(body)) {
            Boolean success= (Boolean) JSONUtil.parseObj(body).get("success");
            if (!success) {
                log.error("打卡失败即将再次打卡");
                startTask();
            }
        }
    }

}
