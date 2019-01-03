package com.zhou.redisshare.filter;

import com.zhou.redisshare.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhoumb on 2019/1/2
 */
@Component
public class CardShotScreenSelfInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private TestService testService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        testService.test();
        System.out.println("执行拦截 通过");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        return true;
    }
}
