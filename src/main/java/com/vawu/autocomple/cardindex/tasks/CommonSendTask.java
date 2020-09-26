package com.vawu.autocomple.cardindex.tasks;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.vawu.autocomple.cardindex.config.TimeConfig;
import com.vawu.autocomple.cardindex.utils.AddressResolutionUtil;
import com.vawu.autocomple.cardindex.utils.TimeChoiceSedUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@Configuration("commonSendTask")
@Slf4j
public class CommonSendTask {

    @Value("${stu.timeTemp:5000}")
    String timeTemp;
    @Autowired
    TimeConfig timeConfig;
    @Autowired
    TimeChoiceSedUtil sed;
    @Autowired
    AddressResolutionUtil utils;

    public void startTask() {
        HttpResponse result2 = HttpRequest.post(timeConfig.getUrls().get(sed.getSed()))
                .header(Header.USER_AGENT, timeConfig.getMy_headers()[RandomUtil.randomInt(0, 10)])//头信息，多个头信息多次调用此方法即可
                .timeout(60000)//超时，毫秒
                .setReadTimeout(180000)
                .execute();
        log.info("开始打卡-------->时间:" + sed.getSed());
        log.info("详细地址:" + utils.getAddress());
        log.info("省:" + utils.getProvince() + " 市:" + utils.getCity() + " 区:" + utils.getCounty());
        log.info(result2.body());
        log.info(Integer.toString(result2.getStatus()));
        log.info("打卡结束-------------------------------------------------------------->");
    }
}
