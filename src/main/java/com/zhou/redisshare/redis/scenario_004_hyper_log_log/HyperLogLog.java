package com.zhou.redisshare.redis.scenario_004_hyper_log_log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 记录页面访问人数
 * Created by zhoumb on 2019/1/3
 */
@Component
public class HyperLogLog {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 记录页面访问的用户
     * @param pageName
     * @param userId
     * @return
     */
    public Long logViewUserId(String pageName, String... userId) {
        return redisTemplate.opsForHyperLogLog().add(pageName, userId);
    }

    public Long getPageViewCount(String pageName) {
        return redisTemplate.opsForHyperLogLog().size(pageName);
    }
}
