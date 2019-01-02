package com.zhou.redisshare.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhoumb on 2019/1/2
 */
@RestController
public class PingController {

    @GetMapping(value = "/ping")
    public String ping() {
        return "pong";
    }
}
