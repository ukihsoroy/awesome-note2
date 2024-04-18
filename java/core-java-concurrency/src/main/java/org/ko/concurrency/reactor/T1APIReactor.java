package org.ko.concurrency.reactor;

import reactor.core.publisher.Flux;

public class T1APIReactor {

    public static void main(String[] args) {

        /**
         * 单线程- 都在主线程
         */
        Flux.generate(
                () -> 0,
                (value, sink) -> {

                    if (value == 3) {
                        //主动完成
                        sink.complete();
                    } else {
                        sink.next("value: " + value);
                    }

                    return value + 1;
                }).subscribe(
                    System.out::println,
                    System.out::println,
                    () -> System.out.println("Subscription is completed!")
                );

    }

}
