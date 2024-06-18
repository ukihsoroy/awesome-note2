package org.ko.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class SynchronizedExample1 {

    //修饰代码块
    public void test1 (int j) {
        /*
         * 作用范围是大括号的代码
         * 作用的对象是调用这个代码的对象
         */
        synchronized (this) {
            for (int i = 0; i < 10; i ++) {
                log.info("test1 - {} - {}", i, j);
            }
        }
    }

    /**
     * 修饰一个方法, 作用范围整个方法，作用对象是调用这个方法的对象
     * 子类没办法集成synchronized这个关键字，需要子类自己声明
     * @param j
     */
    public synchronized void test2 (int j) {
        for (int i = 0; i < 10; i ++) {
            log.info("test2 - {} - {}", i, j);
        }
    }

    public static void main(String[] args) {
        SynchronizedExample1 example1 = new SynchronizedExample1();
        SynchronizedExample1 example2 = new SynchronizedExample1();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> example1.test2(1));
        executorService.execute(() -> example2.test2(2));
    }
}
