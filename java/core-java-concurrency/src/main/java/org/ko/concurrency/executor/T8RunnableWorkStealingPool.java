package org.ko.concurrency.executor;

import org.ko.concurrency.utils.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T8RunnableWorkStealingPool {

    public static void main(String[] args) {

        //创建线程池---给定并行度大小创建的，默认值是主机CPU可用核心数
        ExecutorService executor = Executors.newWorkStealingPool();


        //创建含有三个待执行的程序的集合
        List<Runnable> runnables = Arrays.asList(
                () -> {
                         try {
                            TimeUnit.SECONDS.sleep(1);
                            System.out.println(Thread.currentThread().getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    },
                () -> {
                        try {
                            TimeUnit.SECONDS.sleep(2);
                            System.out.println(Thread.currentThread().getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    },
                () -> {
                        try {
                            TimeUnit.SECONDS.sleep(3);
                            System.out.println(Thread.currentThread().getName());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
        );

        //提交
        runnables.forEach(t -> executor.submit(t));

        ThreadUtils.destory(executor);


    }

    public static Callable<String> callable (String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
