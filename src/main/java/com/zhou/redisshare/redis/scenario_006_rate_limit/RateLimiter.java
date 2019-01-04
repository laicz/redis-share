package com.zhou.redisshare.redis.scenario_006_rate_limit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 实现流量限制
 * Created by zhoumb on 2019/1/4
 */
@Component
public class RateLimiter {
    @Autowired
    private RedisTemplate redisTemplate;

    public void addVisitRecord(String userId, String actionKey) {
        long value = System.currentTimeMillis();
        redisTemplate.opsForZSet().add(createKey(userId, actionKey), value, value);
    }

    private String createKey(String userId, String actionKey) {
        return String.format("hist:%s:%s", userId, actionKey);
    }

    /**
     * 判断是否超出了设定的阈值
     *
     * @param userId
     * @param actionKey
     * @param period
     * @param maxCount
     * @return
     */
    public boolean isActiveLimit(String userId, String actionKey, int period, int maxCount) {
        String key = createKey(userId, actionKey);
        long nowTs = System.currentTimeMillis();
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = connectionFactory.getConnection();
//        connection.openPipeline();
        connection.zAdd(key.getBytes(), nowTs, String.valueOf(nowTs).getBytes());
        connection.zRemRangeByScore(key.getBytes(), 0, nowTs - period * 1000);
        Long count = connection.zCard(key.getBytes());
        connection.expire(key.getBytes(), period + 1);
//        connection.exec();
//        connection.closePipeline();
        return count <= maxCount;
    }
}
