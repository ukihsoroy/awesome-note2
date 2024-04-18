package org.ko.concurrency.executor;

import org.ko.concurrency.utils.ThreadUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class T9ExecutorSchedule {

    public static void main(String[] args) {

        //在经过一段时间后定期运行或一次运行
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        Runnable task = () -> System.out.println("Scheduling: " + System.nanoTime());
        //3秒后运行任务
        ScheduledFuture<?> future = executor.schedule(task, 3, TimeUnit.SECONDS);

        try {
            TimeUnit.MILLISECONDS.sleep(1337);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //getDelay 调用此方法会返回剩余的延迟 延迟过后任务同步执行
        long remainingDelay = future.getDelay(TimeUnit.MILLISECONDS);
        System.out.printf("Remaining Delay: %sms\n", remainingDelay);

        ThreadUtils.destory(executor);
    }
}
