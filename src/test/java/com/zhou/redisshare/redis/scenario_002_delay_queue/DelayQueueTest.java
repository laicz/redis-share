package com.zhou.redisshare.redis.scenario_002_delay_queue;

import com.zhou.redisshare.RedisShareApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhoumb on 2019/1/3
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class DelayQueueTest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testDelayQueue() {
        String key = "q-demo";
        RedisDelayingQueue<String> queue = new RedisDelayingQueue<>(redisTemplate, key);

        Thread producer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.delay("codehole:" + i);
            }
        });

        Thread consumer = new Thread(() -> {
            queue.loop();
        });

        producer.start();
        consumer.start();
        try {
            producer.join();
            Thread.sleep(6000);
            consumer.interrupt();
            consumer.join();
        } catch (InterruptedException e) {
        }

    }

    /**
     * 通过RedisTemplate获取ConnectionFactory
     * 通过ConnectionFactory 获取 Connection
     *
     */
    @Test
    public void bitCommand() {
        Boolean w = redisTemplate.opsForValue().getBit("w", 0);
        RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = connectionFactory.getConnection();
        Long w1 = connection.bitCount("w".getBytes());
        System.out.println(w1);
        System.out.println(connection.bitCount("w".getBytes(),0,10));
//        redisTemplate.opsForValue().setBit();
//        List list = redisTemplate.opsForValue().bitField("w",BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.INT_16.getBits()));
    }
}
