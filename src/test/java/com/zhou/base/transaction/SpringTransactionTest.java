package com.zhou.base.transaction;

import com.zhou.redisshare.RedisShareApplication;
import com.zhou.redisshare.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by zhoumb on 2019/3/8
 */
@ContextConfiguration()
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisShareApplication.class)
public class SpringTransactionTest {
    @Autowired
    private TransactionService transactionService;

    @Test
    public void test() {
        long st = System.currentTimeMillis();
        transactionService.save("测试");
        System.out.println("第一次耗时：" + (System.currentTimeMillis() - st));
        st = System.currentTimeMillis();
        transactionService.save("再次测试");
        System.out.println("第一次耗时：" + (System.currentTimeMillis() - st));
    }
}
