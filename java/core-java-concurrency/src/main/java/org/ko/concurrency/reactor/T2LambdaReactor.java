package org.ko.concurrency.reactor;

import reactor.core.publisher.Flux;

public class T2LambdaReactor {

    public static void main(String[] args) {
        Flux.just(1, 2, 3).map(value -> {
            if (Math.random() < 0.1F) {
                throw new RuntimeException("value must be less than 3!");
            }
            return value + 1;
        }).subscribe(
                System.out::println,        //处理数据 onNext()
                System.out::println,        //处理异常 onError()
                () -> System.out.println("Subscription is completed!")
        );


    }

}
