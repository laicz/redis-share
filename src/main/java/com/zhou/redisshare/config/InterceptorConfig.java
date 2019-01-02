package com.zhou.redisshare.config;

import com.zhou.redisshare.filter.CardShotScreenSelfInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by zhoumb on 2019/1/2
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CardShotScreenSelfInterceptor cardShotScreenSelfInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cardShotScreenSelfInterceptor);
    }
}
