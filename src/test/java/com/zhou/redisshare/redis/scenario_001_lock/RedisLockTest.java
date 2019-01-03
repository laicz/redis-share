package com.zhou.redisshare.redis.scenario_001_lock;

import com.zhou.redisshare.RedisShareApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhoumb on 2019/1/3
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class RedisLockTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testLock() {
        String key = "codehole";
        RedisWithReentrankLock lock = new RedisWithReentrankLock(redisTemplate);
        System.out.println(lock.lock(key));
        System.out.println(lock.lock(key));
        System.out.println(lock.unlock(key));
        System.out.println(lock.unlock(key));
    }
}
