package org.ko.concurrency.executor;

import org.ko.concurrency.utils.ThreadUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class T7CallableWorkStealingPoolSleep {

    public static void main(String[] args) {

        //创建线程池---给定并行度大小创建的，默认值是主机CPU可用核心数
        ExecutorService executor = Executors.newWorkStealingPool();

        //创建含有三个待执行的程序的集合
        List<Callable<String>> callables = Arrays.asList(
                callable("task1", 2),
                callable("task2", 1),
                callable("task3", 3)
        );

        String result = null;
        try {
            //invokeAny 会获取最快返回的那个结果 并且返回
            result = executor.invokeAny(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(result);

        ThreadUtils.destory(executor);


    }

    public static Callable<String> callable (String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
