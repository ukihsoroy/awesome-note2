package org.ko.concurrency.executor;


import org.ko.concurrency.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用java8 线程池创建线程
 */
public class T3SingleThreadExecutor {

    public static void main(String[] args) {

        //创建线程池 可以动态扩充
        ExecutorService executor = Executors.newSingleThreadExecutor();

        //不必创建线程
        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        //java进程不会停止 会一直监听线程 需要手动停止

        //停止线程
        ThreadUtils.destory(executor);

    }
}
