package com.zhou.base.current;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhoumb on 2019/2/26
 */
public class LinkedBlockingQueueTest {
    @Test
    public void testBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            queue.add("任务" + i);
        }
        AtomicInteger count = new AtomicInteger();
        long st = System.currentTimeMillis();
        for (String task : queue) {
            executorService.execute(() -> {
                count.incrementAndGet();
                System.out.println("当前线程" + Thread.currentThread().getName() + "当前时间" + LocalDateTime.now() + " 处理任务:" + task);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        Thread.sleep(20000);
        System.out.println(count.get());
    }

    @Test
    public void cutImage() {
        String avatarUrl = "https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTL4QuXUEtic3kV8ia0fgtxicicZfm4P6ZeiaLcRMbryZAADV9rxXc7oI8k4icwibAUNvPY9oL8Pza1luQ9nA/132";
        if (StringUtils.isNotBlank(avatarUrl) || StringUtils.endsWithAny(avatarUrl, "/46", "/64", "/96", "/132")) {
            avatarUrl = StringUtils.substring(avatarUrl, 0, avatarUrl.lastIndexOf('/')) + "/0";
        }
        System.out.println(avatarUrl);
    }

    @Test
    public void testList() {
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
            if (list.size() == 5) {
                break;
            }
        }
        System.out.println(list);
    }

    @Test
    public void getAscCode() {
        String str = "♒&&&`JSDS";
        int c = (int) str.charAt(0);
        int digit = Character.digit(str.charAt(2), 0);
        System.out.println(c);
        System.out.println(digit);
    }

    @Test
    public void ascCode(){
        char a = 'a';
        System.out.println((a -32) %3);
    }
}
