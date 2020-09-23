package com.vawu.autocomple.cardindex;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.vawu.autocomple.cardindex.config.TimeChoiceSed;
import com.vawu.autocomple.cardindex.config.TimeConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
@ComponentScan("com.vawu")
@SpringBootApplication
public class CardIndexApplication {
    @Autowired
    TimeConfig timeConfig;
    @Autowired
    TimeChoiceSed sed;

    public static void main(String[] args) {
        SpringApplication.run(CardIndexApplication.class, args);
    }

    @Configuration      //1.主要用于标记配置类，兼备Component的效果。
    @EnableScheduling   // 2.开启定时任务
    public class SaticScheduleTask {

        //3.添加定时任务
        //或直接指定时间间隔，例如：5秒
        @Scheduled(fixedRate = 5000)
        private void configureTasks() {
            HttpResponse result2 = HttpRequest.post(timeConfig.getUrls().get(sed.getSed()))
                    .header(Header.USER_AGENT, timeConfig.getMy_headers()[RandomUtil.randomInt(0, 12)])//头信息，多个头信息多次调用此方法即可
                    .timeout(20000)//超时，毫秒
                    .execute();
        }
    }
}
