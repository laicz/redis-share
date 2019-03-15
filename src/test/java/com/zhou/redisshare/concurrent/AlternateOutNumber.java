package com.zhou.redisshare.concurrent;

/**
 * 两条线程交替数据0,1,2,4
 * Created by zhoumb on 2019/3/12
 */
public class AlternateOutNumber {
    private Object lock = new Object();
    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        AlternateOutNumber alternateOutNumber = new AlternateOutNumber();
        Thread thread = new Thread(alternateOutNumber.new Turning("偶数"), "线程1");
        Thread thread2 = new Thread(alternateOutNumber.new Turning("基数"), "线程2");
        thread.start();
        Thread.sleep(1000);
        thread2.start();
    }

    class Turning implements Runnable {
        private String tag;

        public Turning(String tag) {
            this.tag = tag;
        }

        @Override
        public void run() {
            while (count < 100) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + "输出：" + tag + " : " + count++);
                    lock.notifyAll();
                    if (count < 100) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
