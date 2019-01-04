package com.zhou.redisshare.redis.scenario_006_rate_limit;

import com.zhou.redisshare.RedisShareApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhoumb on 2019/1/4
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class RateLimitTest {

    @Autowired
    private RateLimiter rateLimiter;

    @Test
    public void testRateLimit() {
        String userId = "testUserId";
        String actionKey = "testActionKey";
        for (int i = 0; i < 10; i++) {

            System.out.println(rateLimiter.isActiveLimit(userId, actionKey, 5, 5));
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
