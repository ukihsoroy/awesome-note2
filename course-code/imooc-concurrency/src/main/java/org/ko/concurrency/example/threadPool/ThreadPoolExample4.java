package org.ko.concurrency.example.threadPool;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolExample4 {

    public static void main(String[] args) {

        //只有一个线程的线程池
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(5);

//        exec.schedule(() -> log.info("schedule run!"), 5, TimeUnit.SECONDS);
//
//        exec.scheduleAtFixedRate(() -> log.info("schedule at fixed run!"), 1, 3, TimeUnit.SECONDS);

        //exec.shutdown();

        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                log.info("time task run!");
            }
        }, new Date(), 5 * 1000);
    }
}
