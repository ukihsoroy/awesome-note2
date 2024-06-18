package org.ko.concurrency.example.atomic;

import lombok.extern.slf4j.Slf4j;
import org.ko.concurrency.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@ThreadSafe
public class AtomicExample6 {

    public static AtomicBoolean isHappened = new AtomicBoolean();

    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    test();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

        executorService.shutdown();
        log.info("count: {}", isHappened.get());
    }
    public static void test () {
        if (isHappened.compareAndSet(false, true)) {
            log.info("execute");
        }
    }
}