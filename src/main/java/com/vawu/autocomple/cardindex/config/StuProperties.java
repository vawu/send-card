package com.vawu.autocomple.cardindex.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "stu",ignoreInvalidFields = false)
public class StuProperties {
    private String phone;
    private String timeTemp;
    private String address;
}
