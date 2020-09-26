package com.vawu.autocomple.cardindex;

import com.vawu.autocomple.cardindex.data.StuProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Slf4j
@EnableConfigurationProperties(StuProperties.class)
@ComponentScan("com.vawu")
@SpringBootApplication
public class CardIndexApplication {

    @PostConstruct
    private void init() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
    }

    public static void main(String[] args) {
        SpringApplication.run(CardIndexApplication.class, args);
    }

}
