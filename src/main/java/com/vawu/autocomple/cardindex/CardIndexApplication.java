package com.vawu.autocomple.cardindex;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.vawu.autocomple.cardindex.config.StuProperties;
import com.vawu.autocomple.cardindex.config.TimeChoiceSed;
import com.vawu.autocomple.cardindex.config.TimeConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@EnableConfigurationProperties(StuProperties.class)
@ComponentScan("com.vawu")
@SpringBootApplication
public class CardIndexApplication {
    @Autowired
    TimeConfig timeConfig;
    @Autowired
    TimeChoiceSed sed;
    @Autowired
    AddressResolutionUtil utils;
    @Value("${stu.timeTemp:5000}")
    String timeTemp;

    Integer intTime = 5000;

    @PostConstruct
    private void init() {
        intTime = Integer.parseInt(timeTemp);
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static void main(String[] args)
    {
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
                    .header(Header.USER_AGENT, timeConfig.getMy_headers()[RandomUtil.randomInt(0, 10)])//头信息，多个头信息多次调用此方法即可
                    .timeout(60000)//超时，毫秒
                    .execute();
            log.info("开始打卡-------->时间:" + sed.getSed());
            log.info("详细地址:"+utils.getAddress());
            log.info("省:" + utils.getProvince()+" 市:"+utils.getCity()+" 区:"+utils.getCounty());
            log.info(result2.body());
            log.info(Integer.toString(result2.getStatus()));
            log.info("打卡结束----------------");
        }
    }


}
