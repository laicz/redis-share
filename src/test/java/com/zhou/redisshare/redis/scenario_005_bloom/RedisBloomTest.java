package com.zhou.redisshare.redis.scenario_005_bloom;

import com.zhou.redisshare.RedisShareApplication;
import io.lettuce.core.RedisClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhoumb on 2019/1/3
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class RedisBloomTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testLettuce() {
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        LettuceConnection connection = (LettuceConnection) connectionFactory.getConnection();
    }
}
