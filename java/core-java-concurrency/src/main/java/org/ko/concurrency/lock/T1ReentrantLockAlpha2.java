package org.ko.concurrency.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T1ReentrantLockAlpha2 {

    /**
     * 获取锁的线程数量
     */
    public static int lockCount = 0;

    /**
     * 为获取锁的线程数量
     */
    public static int unlockCount = 0;

    public static void main(String[] args) {

        /**
         * ReentrantLock --一个可重入的互斥锁
         */
        Lock lock = new ReentrantLock();

        while (true) {
            new Thread(() -> {
                //尝试获取锁
                if (lock.tryLock()) {
                    //处理获取锁的业务逻辑
                    try {
//                        TimeUnit.SECONDS.sleep(1);
                        String name = Thread.currentThread().getName();
                        System.out.println("Lock thread name: " + name);
                        lockCount ++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                       //必须要释放锁
                       lock.unlock();
                    }

                } else {
                    //处理未获得锁的业务逻辑
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    String name = Thread.currentThread().getName();
                    System.out.println("UnLock thread name: " + name);
                    unlockCount ++;
                }
            }).start();

            if (unlockCount > 500) {
                break;
            }
        }

        System.out.println("Lock count: " + lockCount);
        System.out.println("UnLock count: " + unlockCount);

    }
}

