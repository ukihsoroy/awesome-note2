package org.ko.concurrency.executor;

import org.ko.concurrency.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T12ExecutorTest1 {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });


        executor.submit(() -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        });

        ThreadUtils.destory(executor);

    }

}
