package com.zhou.concurrency;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhoumb on 2019/2/25
 */
public class CommonVariable {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private static boolean bool = true;

    public boolean isBool() {
        return bool;
    }

    public void setBool(boolean bool) {
        this.bool = bool;
    }

    public void print() {
        while (bool) {
            System.out.println("当前时间：" + format.format(new Date()));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
