package org.ko.concurrency.local;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T1ThreadLocal {

    String name = "K.O";

    public static void main(String[] args) {
//        ThreadLocal<T1ThreadLocal> local = new ThreadLocal<>();
//        local.set(new T1ThreadLocal());
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("K.O");

        ExecutorService executor = Executors.newWorkStealingPool();

        List<Runnable> runnables = Arrays.asList(
                () -> System.out.println(local.get()),
                () -> {
                    System.out.println("----1");
//                    T1ThreadLocal t1 = new T1ThreadLocal();
//                    t1.name = "Sultan";
                    local.set("Sultan");
                },
                () -> System.out.println(local.get())
        );

        runnables.forEach(r -> executor.submit(r));

//        executor.shutdown();
    }

    @Override
    public String toString() {
        return "T1ThreadLocal{" +
                "name='" + name + '\'' +
                '}';
    }
}
