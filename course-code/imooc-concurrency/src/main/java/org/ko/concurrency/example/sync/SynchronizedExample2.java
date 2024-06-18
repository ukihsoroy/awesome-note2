package org.ko.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample2 {

    //修饰一个类
    public static void test1 (int j) {
        /*
         * 作用范围是大括号的代码
         * 作用的对象是这个类所有对象
         */
        synchronized (SynchronizedExample2.class) {
            for (int i = 0; i < 10; i ++) {
                log.info("test1 - {} - {}", i, j);
            }
        }
    }

    /**
     * 修饰一个静态方法, 作用范围整个方法，作用对象是这个类的所有对象
     * @param j
     */
    public static synchronized void test2 (int j) {
        for (int i = 0; i < 10; i ++) {
            log.info("test2 - {} - {}", i, j);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> test1(1));
        executorService.execute(() -> test1(2));
    }
}
