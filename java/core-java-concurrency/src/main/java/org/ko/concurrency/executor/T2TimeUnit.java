package org.ko.concurrency.executor;

import java.util.concurrent.TimeUnit;

/**
 * 使用java8 并发包枚举
 */
public class T2TimeUnit {

    public static void main(String[] args) {

        Runnable runnable = () -> {
            try {
                String name = Thread.currentThread().getName();
                System.out.println("Foo " + name);
                //TimeUnit并发包中的新api 和Thread.sleep(1000);一样
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Bar " + name);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
