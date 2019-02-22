package com.zhou.base.atomic;

/**
 * 双重确认实现单例模式
 * Created by zhoumb on 2019/2/22
 */
public class DoubleCheck {

    private static volatile DoubleCheck doubleCheck = null;

    private DoubleCheck() {
    }

    public DoubleCheck getInstance() {
        if (doubleCheck == null) {
            synchronized (DoubleCheck.class) {
                this.doubleCheck = new DoubleCheck();
            }
        }

        return doubleCheck;
    }

}
