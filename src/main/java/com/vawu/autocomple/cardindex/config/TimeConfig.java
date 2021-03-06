package com.vawu.autocomple.cardindex.config;

import com.vawu.autocomple.cardindex.tasks.SaticScheduleTask;
import com.vawu.autocomple.cardindex.utils.AddressResolutionUtil;
import com.vawu.autocomple.cardindex.utils.TimeUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Data
@Configuration
public class TimeConfig {
    @Value("${stu.phone:00000000}")
    String phone;
    @Value("${stu.timeTemp:5000}")
    String timeTemp;
    @Autowired
    AddressResolutionUtil utils;
    String mon_url;
    String night_url;
    String mid_url;
    Map<String, String> urls = new HashMap<>();

    @PostConstruct
    private void init() {
        if (!"666".equals(timeTemp)) {
            scheduledChoiceSed();
        }
    }

    String[] my_headers = {
            "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11",
    };

    public Map<String, String> getUrls() {
        String date = TimeUtils.getFormatedDate();
        mon_url = "http://fk.nbcc.cn/fxsq/mrdk/save.htm?phone=" + phone + "&lx=1&mrzctw=正常（36.1-37.3℃）&dw=" + utils.getAddress() + "&kfsj=早上&rq=" +
                date + "&sfzx=";
        night_url = "http://fk.nbcc.cn/fxsq/mrdk/save.htm?phone=" + phone + "&lx=1&mrzctw=正常（36.1-37.3℃）&dw=" + utils.getAddress() + "&kfsj=晚上&rq=" +
                date + "&sfzx=";
        mid_url = "http://fk.nbcc.cn/fxsq/mrdk/save.htm?rq=" +
                date + "&phone=" + phone + "&tw=正常（36.1-37.3℃）&mrzctw" +
                "=正常（36.1-37.3℃）&znl=" + utils.getAddress() + "&jrqk=正常&jrqk1=0&jrqk2&jrqk3&jcjwry=没有&sfyjkm=绿码&jcxgfyry=没有&qgyy" +
                "=没有&qgyynr&lx=1&shen=" + utils.getProvince() + "&shi=" + utils.getCity() + "&qu=" + utils.getCounty() + "&zsbdk=0&kfsj=中午&zzdx=没有上述情况 ";
        urls.put("早上", mon_url);
        urls.put("中午", mid_url);
        urls.put("晚上", night_url);
        return urls;
    }


    private void scheduledChoiceSed() {
        try {
            Method method = SaticScheduleTask.class.getDeclaredMethod("configureTasks");
            Scheduled foo = method.getAnnotation(Scheduled.class);
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(foo);
            Field values = invocationHandler.getClass().getDeclaredField("memberValues");
            values.setAccessible(true);
            Map memberValues = (Map) values.get(invocationHandler);
            memberValues.put("fixedRate", Long.parseLong(timeTemp));
        } catch (Exception e) {
            log.error("----->反射出现异常请通知开发人员进行处理");
        }


    }
}