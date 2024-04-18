package org.ko.concurrency.executor;

/**
 * 使用java8 lambda创建一个线程
 */
public class T1LambdaCreateThread {

    public static void main(String[] args) {

        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName);
        };
        task.run();
        Thread thread = new Thread(task);
        thread.start();
        System.out.println("done!");
    }
}
