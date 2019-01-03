package com.zhou.redisshare.redis.scenario_004_hyper_log_log;

import com.zhou.redisshare.RedisShareApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;

/**
 * Created by zhoumb on 2019/1/3
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class HyperLogLogTest {
    @Autowired
    private HyperLogLog hyperLogLog;

    @Test
    public void logTet() {
        String pageName = "page:index4";
//        String[] userIds = new String[400000];
        String prefix = "laic_z";
        for (int i = 0; i < 900000000; i++) {
//            userIds[i] = String.valueOf(prefix + new Random().nextInt(400000));
            hyperLogLog.logViewUserId(pageName, prefix + i);
        }
//        System.out.println(hyperLogLog.logViewUserId(pageName, userIds));
    }

    @Test
    public void logCount() {
        String pageName = "page:index4";
        System.out.println(hyperLogLog.getPageViewCount(pageName));
    }
}
