package org.ko.concurrency.example.commonUnsafe;

import lombok.extern.slf4j.Slf4j;
import org.ko.concurrency.annoations.NotThreadSafe;

import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@Slf4j
@NotThreadSafe
public class DataFormatExample1 {

    //不是一个线程安全调用
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(("yyyyMMdd"));


    //请求总数
    public static int clientTotal = 5000;

    //同时并发执行的线程数
    public static int threadTotal = 200;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i ++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    update();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception: ", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
    }

    private static void update () {
        try {
            simpleDateFormat.parse("20190223");
        } catch (Exception e) {
            log.error("parse error: {}", e);
        }
    }
}
