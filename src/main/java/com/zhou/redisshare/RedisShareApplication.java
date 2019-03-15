package com.zhou.redisshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class RedisShareApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisShareApplication.class, args);
    }

}

