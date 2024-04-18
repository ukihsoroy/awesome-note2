package org.ko.concurrency.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * volatile 的错误用法
 * 只能保证值的线程可见性
 */
public class T2VolatileError {

//    public volatile static int count = 0;
    public static Integer count = 0;

    public static void inc() {

        //这里延迟1毫秒，使得结果明显
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {

        }
        /**
         * count++ 并不是原子性
         * 1) 首先从内存中读取
         * 2) 进行相加操作
         * 3) 将值重新写入内存
         */
//        count++;
        /**
         * 所以这里需要加入synchronized
         */
        synchronized (count) {
            count++;
        }
    }

    public static void main(String[] args) {
        //同时启动1000个线程，去进行i++计算，看看实际结果
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> T2VolatileError.inc()).start();
        }
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //这里每次运行的值都有可能不同,可能为1000
        System.out.println("运行结果:Counter.count=" + T2VolatileError.count);
    }
}
