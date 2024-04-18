package org.ko.concurrency.executor;


import org.ko.concurrency.utils.ThreadUtils;

import java.util.concurrent.*;

public class T5CallableFuture {

    public static void main(String[] args) {

        //创建单线程线程池
        ExecutorService executor = Executors.newFixedThreadPool(1);

        //提交线程并异步等待返回结果
        Future<Integer> future = executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        });

        try {
            //设置最大等待1秒的时间-超时会抛TimeOutException
            future.get(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        //销毁线程池
        ThreadUtils.destory(executor);
    }
}
