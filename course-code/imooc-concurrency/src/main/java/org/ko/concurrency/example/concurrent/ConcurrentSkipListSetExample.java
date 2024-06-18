package org.ko.concurrency.example.concurrent;

import lombok.extern.slf4j.Slf4j;
import org.ko.concurrency.annoations.ThreadSafe;

import java.util.Set;
import java.util.concurrent.*;

@Slf4j
@ThreadSafe
public class ConcurrentSkipListSetExample {

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    private static Set<Integer> set = new ConcurrentSkipListSet<>();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++) {
            final int j = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add(j);
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count: {}", set.size());
    }

    private static void add (int i) {
        set.add(i);
    }
}
