package com.vawu.autocomple.cardindex;

import com.vawu.autocomple.cardindex.utils.AddressResolutionUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
class CardIndexApplicationTests {
    public static Map<String, String> addressResolution(String address) {
        String regex = "(?<province>[^省]+自治区|.*?省|.*?行政区|.*?市)(?<city>[^市]+自治州|.*?地区|.*?行政单位|.+盟|市辖区|.*?市|.*?县)(?<county>[^县]+县|.+区|.+市|.+旗|.+海域|.+岛)?(?<town>[^区]+区|.+镇)?(?<village>.*)";
        Matcher m = Pattern.compile(regex).matcher(address);
        String province=null, city=null, county=null, town=null, village=null;
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
    @Test
    void  contextLoads() {
        Map map = addressResolution("浙江省宁波市鄞州区曙光北路120号");
        System.out.println(map.get("province"));
        System.out.println(map.get("city"));

    }

}
