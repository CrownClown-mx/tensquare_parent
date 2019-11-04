package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringApplication.class);
    }
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(0,1);
    }
}
