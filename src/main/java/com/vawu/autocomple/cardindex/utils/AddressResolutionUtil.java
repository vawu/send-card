package com.vawu.autocomple.cardindex.utils;

import cn.hutool.core.map.MapUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Slf4j
@Configuration
public class AddressResolutionUtil {
    @Value("${stu.address:浙江省宁波市鄞州区曙光北路120号}")
    String address;

    String province;
    String city;
    String county;

    @PostConstruct
    public void init() {
        Map<String, String> provMaps;
        if (!address.isEmpty() && !address.equals("null")) {
            provMaps = addressResolution(address);
            if (!MapUtil.isEmpty(provMaps)) {
                province = provMaps.get("province");
                city = provMaps.get("city");
                county = provMaps.get("county");
            }
        } else {
            for (int i = 0; i < 10; i++) {
                log.error("------>请确认地址是否配置成功");
            }
        }
    }

    /**
     * 解析地址
     *
     * @param address
     * @return
     */
    public static Map<String, String> addressResolution(String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province = null, city = null, county = null, town = null, village = null;
        Map<String, String> row = null;
        while (m.find()) {
            row = new LinkedHashMap<String, String>();
            province = m.group("province");
            row.put("province", province == null ? "" : province.trim());
            city = m.group("city");
            row.put("city", city == null ? "" : city.trim());
            county = m.group("county");
            row.put("county", county == null ? "" : county.trim());
            town = m.group("town");
            row.put("town", town == null ? "" : town.trim());
            village = m.group("village");
            row.put("village", village == null ? "" : village.trim());
        }
        return row;
    }


}