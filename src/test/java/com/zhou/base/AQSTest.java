package com.zhou.base;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 对AbstractQueuedSynchronize的练习
 * Created by zhoumb on 2019/2/20
 */
public class AQSTest {

    @Test
    public void testReentrantLock() throws InterruptedException {
        /*ReentrantLock reentrantLock = new ReentrantLock(true);
        reentrantLock.lock();
        reentrantLock.lock();
        reentrantLock.unlock();
        reentrantLock.unlock();
        new Thread(() -> {
            if (reentrantLock.tryLock()){
                System.out.println("获取锁成功");
                reentrantLock.unlock();
            }
        }).start();

        Thread.sleep(10000);*/
        ReentrantLock reentrantLock = new ReentrantLock();
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println("线程1 -- 获取到锁");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println("线程2 -- 获取到锁");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        }).start();
        Thread.sleep(1000);
        new Thread(() -> {
            reentrantLock.lock();
            System.out.println("线程3 -- 获取到锁");
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            reentrantLock.unlock();
        }).start();
        Thread.sleep(50000);
    }

    @Test
    public void testComp() throws InterruptedException {
        List<Date> dateList = new ArrayList<>();
        dateList.add(new Date());
        Thread.sleep(100);
        dateList.add(new Date());
        Thread.sleep(100);
        dateList.add(new Date());
        Thread.sleep(100);
        dateList.add(new Date());
        Collections.sort(dateList, new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.after(o2) ? -1 : 1;
            }
        });
        for (Date date : dateList) {
            System.out.println(date.getTime());
        }
    }

    @Test
    public void selfInterrupted() {
        boolean interrupted = Thread.interrupted();
        System.out.println(interrupted);
    }

    /**
     * 读写分离锁的练习
     */
    @Test
    public void readAndWriteLock() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        readLock.lock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        writeLock.lock();
    }

    @Test
    public void lockSupport() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("执行 ... " + simpleDateFormat.format(new Date()));
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(5));
        System.out.println("再来 ... " + simpleDateFormat.format(new Date()));
    }
}
