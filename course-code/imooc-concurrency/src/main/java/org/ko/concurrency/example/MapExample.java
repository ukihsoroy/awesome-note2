package org.ko.concurrency.example;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class MapExample {

    private static Map<Integer, Integer> map = Maps.newHashMap();

//    private static int threadNum = 200;
    private static int threadNum = 1;
    private static int clientNum = 5000;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadNum);
        for (int index = 0; index < clientNum; index ++) {
            final int i = index;
            exec.execute(() -> {
                try {
                    semaphore.acquire();
                    func(i);
                    semaphore.release();
                } catch (Exception e) {
                    System.out.println("Exception: " + e.getMessage());
                }
            });
        }
        exec.shutdown();
        System.out.println("size: " + map.size());
    }

    private static void func (int index) {
        map.put(index, index);
    }

}
