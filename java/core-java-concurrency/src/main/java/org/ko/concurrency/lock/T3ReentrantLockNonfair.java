package org.ko.concurrency.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @see ReentrantLock
 * 有两个构造函数
 * 1) 公平锁 保证线程先进入就可以先获得锁 但因为保证公平所以更耗性能
 * @see ReentrantLock.FairSync
 * 2) 不公平锁 线程不是根据初始化顺序获取锁 而是随机的 更节省资源
 * @see ReentrantLock.NonfairSync
 * 选取合适的方式来创建
 */
public class T3ReentrantLockNonfair implements Runnable{


    ReentrantLock lock = new ReentrantLock();

    public void get() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        set();
        lock.unlock();
    }

    public void set() {
        lock.lock();
        System.out.println(Thread.currentThread().getId());
        lock.unlock();
    }

    @Override
    public void run() {
        get();
    }

    public static void main(String[] args) {
        T3ReentrantLockNonfair ss = new T3ReentrantLockNonfair();
        /**
         * 每一个线程被lock锁了两次 也只有释放两次 才是真正的释放内存
         */
        new Thread(ss).start();
        new Thread(ss).start();
        new Thread(ss).start();
    }
}