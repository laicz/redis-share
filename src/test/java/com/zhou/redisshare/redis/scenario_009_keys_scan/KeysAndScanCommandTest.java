package com.zhou.redisshare.redis.scenario_009_keys_scan;

import com.zhou.redisshare.RedisShareApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.Set;

/**
 * keys 和 scan 命令使用
 * Created by zhoumb on 2019/1/4
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class KeysAndScanCommandTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testKey() {
        Set hole = redisTemplate.keys("code*");
        Iterator iterator = hole.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    @Test
    public void testScan(){
        System.out.println(System.currentTimeMillis());
    }
}
