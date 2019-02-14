package com.zhou.redisshare.redis.init_redis;

import com.zhou.redisshare.RedisShareApplication;
import com.zhou.utils.SnowFlake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 阻塞IO：当我们调用套接字的读写方法，默认他们是阻塞的
 * read 方法需要传递进去一个参数n，表示最多读取这么多字节后返回，如果一个字节都没有，则该线程会阻塞在当前位置，知道该套接字关闭获取获取到数据
 * write一般来说是不会阻塞的，除非内核为套接字分配的缓冲去已经满了，write 方法就会阻塞，直到缓存区有空闲空间挪出来
 * <p>
 * 非阻塞IO：在套接字对象上提供一个选徐昂Non_Blocking,当这个选项打开时，读写的方法不会阻塞，而且是能读多少读多少，能写多少
 * 是多少，这个取决于内核为套接字分配的读缓存区内部的数据字节数，能写多少取决于内核为套接字分配的写缓冲区的空闲空间字节数，
 * 读方法和写方法都会通过返回值来告诉程序实际读写了多少
 * Created by zhoumb on 2019/1/3
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void initRedisTemplate() {
        System.out.println(redisTemplate.opsForValue().setIfAbsent("hello", "hello world"));
    }

    @Test
    public void getCurrentTimeMillis() {
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void test() {
        do {
            for (int i = 0; i < 1000; i++) {
                System.out.println(i);
                if (i >= 5) {
                    break;
                }
            }
            System.out.println("do while ... ");
            break;
        } while (true);
    }
}
