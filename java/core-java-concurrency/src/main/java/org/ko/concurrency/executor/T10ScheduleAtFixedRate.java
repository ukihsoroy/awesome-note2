package org.ko.concurrency.executor;

import org.ko.concurrency.utils.ThreadUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 设置延迟频率的线程定时任务
 * 不考虑任务执行时间的版本
 */
public class T10ScheduleAtFixedRate {

    public static void main(String[] args) {

        //创建线程池
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        //创建待执行代码段
        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());

        //设置延迟
        int initialDelay = 0;

        //设置频率
        int period = 1;

        //执行
        //scheduleAtFixedRate() 不会考虑任务的时间，如果任务要2秒才能完成 而频率只是设定1秒 那么线程池会更效率工作
        executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

        //停止
        ThreadUtils.destory(executor);



    }
}
