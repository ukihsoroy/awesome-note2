package org.ko.concurrency.reactor;

import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.concurrent.TimeUnit;

public class T3FluxAsyncReactor {

    public static void main(String[] args) throws InterruptedException {
        //当前线程执行
        Flux.range(0, 10)
                .publishOn(Schedulers.immediate())
                .subscribe(System.out::println);

        //单线程异步执行
        Flux.range(0, 10)
                .publishOn(Schedulers.single())
                .subscribe(System.out::println);

        //弹性线程池异步执行
        Flux.range(0, 10)
                .publishOn(Schedulers.elastic())
                .subscribe(System.out::println);

        //并行线程池异步执行
        Flux.range(0, 10)
                .publishOn(Schedulers.parallel())
                .subscribe(System.out::println);


        TimeUnit.SECONDS.sleep(5);
    }

}
