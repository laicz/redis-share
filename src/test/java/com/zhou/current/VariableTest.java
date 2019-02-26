package com.zhou.current;

import org.junit.Test;

/**
 * Created by zhoumb on 2019/2/25
 */
public class VariableTest {

    @Test
    public void test() throws InterruptedException {
        new Thread(() -> {
            new CommonVariable().print();
        }).start();

        Thread.sleep(10000);

        new Thread(() -> {
            new CommonVariable().setBool(false);
        }).start();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
