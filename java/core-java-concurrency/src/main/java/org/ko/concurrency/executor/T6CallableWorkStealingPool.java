package org.ko.concurrency.executor;


import org.ko.concurrency.utils.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T6CallableWorkStealingPool {

    public static void main(String[] args) {
        //创建可以自动扩容线程池
        ExecutorService executor = Executors.newWorkStealingPool();

        //定义多个任务
        List<Callable<String>> callables = Arrays.asList(
                () -> "task1",
                () -> "task2",
                () -> "task3");

        //提交并发
        try {
            //invokeAll 会等待全部的线程返回结果
            //List<Future<String>> futures = executor.invokeAll(callables);
            executor.invokeAll(callables).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        }
                        catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //销毁线程池
        ThreadUtils.destory(executor);
    }
}
