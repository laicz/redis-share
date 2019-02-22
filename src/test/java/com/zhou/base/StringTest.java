package com.zhou.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Lists;
import com.zhou.utils.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

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

    @Test
    public void hashCodeTest() {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
    }

    @Test
    public void fastJsonTest() {
        String s = JSON.toJSONString(new Date()/*,new SerializerFeature[]{}*/);       //设置转换类型
        System.out.println(s);
    }

    @Test
    public void springAssertTest() {
        Assert.notNull("", "test spring assert test is null ");
//        Assert.isNull();
    }

    @Test
    public void casTest() {
        AtomicBoolean needFresh = new AtomicBoolean(Boolean.FALSE);
        /**
         * expect:期望的原始值
         * update:期望修改成的值
         */
        boolean b = needFresh.compareAndSet(false, true);
        System.out.println("needFresh=" + needFresh + ", freshResult=" + b);
    }

    @Test
    public void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.refresh();
    }

    @Test
    public void testLinkedList() {
        LinkedList<Object> linkedList = Lists.newLinkedList();
//        System.out.println(linkedList.getFirst());
//        System.out.println(linkedList.getLast());
    }
}
