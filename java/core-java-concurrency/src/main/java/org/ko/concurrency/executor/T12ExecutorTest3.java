package org.ko.concurrency.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class T12ExecutorTest3 {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        Callable<Integer> intTask = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("intTask=" + name);
            return 100;
        };

        Callable<String> strTask = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("strTask=" + name);
            return "hello world!";
        };

        Callable<Boolean> boolTask = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("boolTask=" + name);
            return false;
        };


        Future<Integer> future1 = executor.submit(intTask);
        Future<String> future2 = executor.submit(strTask);
        Future<Boolean> future3 = executor.submit(boolTask);
        System.out.println("future1 done" + future1.isDone());
        System.out.println("future2 done" + future2.isDone());
        System.out.println("future3 done" + future3.isDone());

        Integer result1 = future1.get();
        String result2 = future2.get();
        boolean result3 = future3.get();

        System.out.println("future done1" + future1.isDone());
        System.out.println("future done2" + future2.isDone());
        System.out.println("future done3" + future3.isDone());
        System.out.println("result1 = " + result1);
        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);
    }
}
