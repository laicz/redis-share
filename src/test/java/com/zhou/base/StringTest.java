package com.zhou.base;

import com.zhou.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhoumb on 2019/2/12
 */
public class StringTest {
    @Test
    public void testJoin() {
        String str = "111";
        String[] split = StringUtils.split(str, ",");
        System.out.println(Arrays.asList(split));
    }

    @Test
    public void testFor() {
        int j = 0;
        for (int i = 0; i < 100; i++) {
            j = j++;
        }
        System.out.println(j);
    }

    @Test
    public void testArraysToList() {
        int[] test = new int[]{1, 2, 3, 4};
        List<int[]> ints = Arrays.asList(test);
        System.out.println(ints.size());
    }

    @Test
    public void getTimeMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        System.out.println(calendar.getTimeInMillis());
    }

    @Test
    public void testSnowFlake() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 1000; j++) {
                    System.out.println(SnowFlake.nextId());
                }
            });
        }
        Thread.sleep(10000);
    }
}
