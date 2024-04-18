package org.ko.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ThreadPoolExample2 {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 10; i ++) {
            final int index = i;
            exec.execute(() -> {
                log.info("task:{}", index);
            });
        }
        exec.shutdown();
    }
}
