package org.ko.concurrency.executor;

import org.ko.concurrency.utils.ThreadUtils;

import java.util.concurrent.*;

public class T4SubmitExecutor {

    public static void main(String[] args) {

        //定义一个有返回值的等待调用的函数
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };

        //创建只有一个线程的线程池
        ExecutorService executor = Executors.newFixedThreadPool(1);
        //提交
        Future<Integer> future = executor.submit(task);
        //false
        System.out.println("future done? " + future.isDone());

        Integer result = null;
        try {
            //future.get()方法-->会等待线程执行结果
            //调用将阻止并等待底层可调用终止--最坏情况可以永久运行
            result = future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("future done? " + future.isDone());
        System.out.println("result: " + result);
        ThreadUtils.destory(executor);
    }
}
