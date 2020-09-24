package com.vawu.autocomple.cardindex.config;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Data
@Configuration
public class TimeConfig {
    @Value("${stu.phone:00000000}")
    String phone;
    String mon_url;
    String night_url;
    String mid_url;
    Map<String, String> urls=new HashMap<>();

    String[] my_headers = {
            "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:30.0) Gecko/20100101 Firefox/30.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/537.75.14",
            "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)",
            "Mozilla/5.0 (Windows; U; Windows NT 5.1; it; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.11",
            "Opera/9.25 (Windows NT 5.1; U; en)",
            "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
            "Mozilla/5.0 (compatible; Konqueror/3.5; Linux) KHTML/3.5.5 (like Gecko) (Kubuntu)",
            "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.8.0.12) Gecko/20070731 Ubuntu/dapper-security Firefox/1.5.0.12",
            "Lynx/2.8.5rel.1 libwww-FM/2.14 SSL-MM/1.4.1 GNUTLS/1.2.9",
            "Mozilla/5.0 (X11; Linux i686) AppleWebKit/535.7 (KHTML, like Gecko) Ubuntu/11.04 Chromium/16.0.912.77 Chrome/16.0.912.77 Safari/535.7",
            "Mozilla/5.0 (X11; Ubuntu; Linux i686; rv:10.0) Gecko/20100101 Firefox/10.0 "
    };

    @PostConstruct
    protected void init() {
        String date = DateUtil.format(DateUtil.date(), "yyyy-MM-dd");
        mon_url = "http://fk.nbcc.cn/fxsq/mrdk/save.htm?phone="+phone+"&lx=1&mrzctw=正常（36.1-37.3℃）&dw=浙江省宁波市鄞州区曙光北路120号&kfsj=上午&rq=" +
                date;
        night_url = "http://fk.nbcc.cn/fxsq/mrdk/save.htm?phone="+phone+"&lx=1&mrzctw=正常（36.1-37.3℃）&dw=浙江省宁波市鄞州区曙光北路120号&kfsj=晚上&rq=" +
                date;
        mid_url = "http://fk.nbcc.cn/fxsq/mrdk/save.htm?rq=" +
                date + "&phone="+phone+"&tw=正常（36.1-37.3℃）&mrzctw" +
                "=正常（36.1-37.3℃）&znl=浙江省宁波市鄞州区曙光北路120号&jrqk=正常&jrqk1=0&jrqk2&jrqk3&jcjwry=没有&sfyjkm=绿码&jcxgfyry=没有&qgyy" +
                "=没有&qgyynr&lx=1&shen=浙江省&shi=台州市&qu=温岭市&zsbdk=0&kfsj=中午&zzdx=没有上述情况 ";
        urls.put("早上", mon_url);
        urls.put("中午", mid_url);
        urls.put("晚上", night_url);
    }
}
