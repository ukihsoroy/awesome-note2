package org.ko.concurrency.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class T11ScheduleWithFixedDelay {

    public static void main(String[] args) {

        //创建线程池
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> {
            try {
                //延迟2秒执行
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Scheduling: " + System.nanoTime());
            }
            catch (InterruptedException e) {
                System.err.println("task interrupted");
            }
        };

        //scheduleWithFixedDelay 会在上一次任务完成后-->开始计时-->时间到-->进行下一次
        executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
    }

}
