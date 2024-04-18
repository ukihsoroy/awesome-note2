package org.ko.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1ReentrantLockAlpha1 {

    private static int count = 0;
    /**
     * 一个可重入的互斥锁
     */
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            new Thread(() -> lock()).start();
        }

        System.out.println(count);
    }

    public static void lock () {
        lock.lock();
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name);
            TimeUnit.SECONDS.sleep(1);
            //处理业务逻辑
            count ++;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}

