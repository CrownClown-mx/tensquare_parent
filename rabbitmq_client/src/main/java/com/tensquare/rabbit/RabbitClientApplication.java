package com.tensquare.rabbit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.SmsUtil;

/**
 * Created by WF on 2019-11-06 15:01
 */
@SpringBootApplication
public class RabbitClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitClientApplication.class);
    }
    @Bean
    public SmsUtil smsUtil(){
        return new SmsUtil();
    }
}
