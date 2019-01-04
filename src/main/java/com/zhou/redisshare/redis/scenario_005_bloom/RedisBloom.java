package com.zhou.redisshare.redis.scenario_005_bloom;

import io.lettuce.core.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Redis使用Bloom过滤器
 * Created by zhoumb on 2019/1/3
 */
@Component
public class RedisBloom {
    @Autowired
    private RedisTemplate redisTemplate;


    public void addItem(String key, String value) {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = connectionFactory.getConnection();
    }
}
