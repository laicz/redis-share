package com.zhou.redisshare.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by zhoumb on 2019/2/19
 */
@Component
public class ApplicationContextHolder implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("////////////////////////////////////////////");
        System.out.println(applicationContext.getClass().getName());
        System.out.println("////////////////////////////////////////////");
    }
}
