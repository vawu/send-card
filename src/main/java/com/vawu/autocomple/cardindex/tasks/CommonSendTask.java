package com.vawu.autocomple.cardindex.tasks;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.vawu.autocomple.cardindex.config.TimeConfig;
import com.vawu.autocomple.cardindex.utils.AddressResolutionUtil;
import com.vawu.autocomple.cardindex.utils.TimeChoiceSedUtil;
import com.vawu.autocomple.cardindex.utils.TimeUtils;
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

    public HttpResponse startTask() {
        HttpResponse result = HttpRequest.post(timeConfig.getUrls().get(sed.getSed()))
                .header(Header.USER_AGENT, timeConfig.getMy_headers()[RandomUtil.randomInt(0, 2)])//头信息，多个头信息多次调用此方法即可
                .timeout(60000)//超时， 毫秒
                .setReadTimeout(120000)
                .execute();
        log.info("开始打卡-------->时间:" + TimeUtils.getFormatedDate() + " " + TimeUtils.getShanghaiHour() + sed.getSed());
        log.info("详细地址:" + utils.getAddress());
        log.info("省:" + utils.getProvince() + " 市:" + utils.getCity() + " 区:" + utils.getCounty());
        log.info(result.body());
        log.info(Integer.toString(result.getStatus()));
        log.info("打卡结束-------------------------------------------------------------->");
        return result;
    }
}
