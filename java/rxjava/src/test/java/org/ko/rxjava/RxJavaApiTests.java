package org.ko.rxjava;

import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import org.junit.jupiter.api.Test;
import org.ko.rxjava.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 基本api使用
 * @link [@https://www.jianshu.com/p/0cd258eecf60]
 */
class RxJavaApiTests {

    private static final Logger logger = LoggerFactory.getLogger(RxJavaApiTests.class);

    /**
     * 基本调用方法
     */
    @Test void testCreate() {
        final StringBuffer rxOperatorsText = new StringBuffer();

        Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(@NonNull ObservableEmitter<Integer> e) {
                //第一次next
                rxOperatorsText.append("Observable emit 1" + "\n");

                //on next 1.
                logger.info("Observable emit 1" + "\n");
                e.onNext(1);

                //on next 2.
                rxOperatorsText.append("Observable emit 2" + "\n");
                logger.info("Observable emit 2" + "\n");
                e.onNext(2);

                //发送到2的时候，后面的需求

                //on next 3.
                rxOperatorsText.append("Observable emit 3" + "\n");
                logger.info("Observable emit 3" + "\n");
                e.onNext(3);

                //完成
                e.onComplete();

                //on next 4
                rxOperatorsText.append("Observable emit 4" + "\n");
                logger.info("Observable emit 4" + "\n" );
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;

            //用完即可丢弃
            private Disposable disposable;

            //订阅
            public void onSubscribe(@NonNull Disposable d) {
                //杀死
                //d.isDisposed() 是否已经被杀死
                rxOperatorsText.append("onSubscribe : ").append(d.isDisposed()).append("\n");
                logger.info("onSubscribe : " + d.isDisposed() + "\n" );
                disposable = d;
            }

            public void onNext(@NonNull Integer integer) {
                rxOperatorsText.append("onNext : value : ").append(integer).append("\n");
                logger.info("onNext : value : " + integer + "\n" );
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    // 切断程序
                    disposable.dispose();
                    rxOperatorsText.append("onNext : isDisposable : ").append(disposable.isDisposed()).append("\n");
                    logger.info("onNext : isDisposable : " + disposable.isDisposed() + "\n");
                }
            }

            public void onError(@NonNull Throwable e) {
                rxOperatorsText.append("onError : value : ").append(e.getMessage()).append("\n");
                logger.info("onError : value : " + e.getMessage() + "\n" );
            }

            public void onComplete() {
                rxOperatorsText.append("onComplete" + "\n");
                logger.info("onComplete" + "\n" );
            }
        });

        print(rxOperatorsText);
    }

    /**
     * map操作
     * map 基本作用就是将一个 Observable 通过某种函数关系，转换为另一种 Observable，
     * 上面例子中就是把我们的 Integer 数据变成了 String 类型。从Log日志显而易见。
     */
    @Test void testMap() {
        final StringBuffer rxOperatorsText = new StringBuffer();

        Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(@NonNull ObservableEmitter<Integer> e) {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).map(new Function<Integer, String>() { // map 对数据进行格式转换映射
            public String apply(@NonNull Integer integer) {
                return "This is result " + integer;
            }
        }).subscribe(new Consumer<String>() {
            public void accept(@NonNull String s) {
                rxOperatorsText.append("accept : ").append(s).append("\n");
                logger.info("accept : " + s +"\n" );
            }
        });

        print(rxOperatorsText);
    }

    /**
     * zip 专用于合并事件，该合并不是连接（连接操作符后面会说），
     * 而是两两配对，也就意味着，最终配对出的 Observable 发射事件数目只和少的那个相同。
     */
    @Test void testZip() {
        final StringBuffer rxOperatorsText = new StringBuffer();

        Observable.zip(getStringObservable(rxOperatorsText), getIntegerObservable(rxOperatorsText),
                new BiFunction<String, Integer, String>() {

            public String apply(@NonNull String s, @NonNull Integer integer) {
                return s + integer;
            }

        }).subscribe(new Consumer<String>() {
            public void accept(@NonNull String s) {
                rxOperatorsText.append("zip : accept : ").append(s).append("\n");
                logger.info("zip : accept : " + s + "\n");
            }
        });

        print(rxOperatorsText);
    }

    private Observable<String> getStringObservable(final StringBuffer rxOperatorsText) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(@NonNull ObservableEmitter<String> e) {
                if (!e.isDisposed()) {
                    e.onNext("A");
                    rxOperatorsText.append("String emit : A \n");
                    logger.info("String emit : A \n");
                    e.onNext("B");
                    rxOperatorsText.append("String emit : B \n");
                    logger.info("String emit : B \n");
                    e.onNext("C");
                    rxOperatorsText.append("String emit : C \n");
                    logger.info("String emit : C \n");
                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable(final StringBuffer rxOperatorsText) {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(@NonNull ObservableEmitter<Integer> e) {
                if (!e.isDisposed()) {
                    e.onNext(1);
                    rxOperatorsText.append("Integer emit : 1 \n");
                    logger.info("Integer emit : 1 \n");
                    e.onNext(2);
                    rxOperatorsText.append("Integer emit : 2 \n");
                    logger.info("Integer emit : 2 \n");
                    e.onNext(3);
                    rxOperatorsText.append("Integer emit : 3 \n");
                    logger.info("Integer emit : 3 \n");
                    e.onNext(4);
                    rxOperatorsText.append("Integer emit : 4 \n");
                    logger.info("Integer emit : 4 \n");
                    e.onNext(5);
                    rxOperatorsText.append("Integer emit : 5 \n");
                    logger.info("Integer emit : 5 \n");
                }
            }
        });
    }

    /**
     * 将两个observable 合并成1个
     * 注意是串行
     * 按参数排序
     */
    @Test void testConcat() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.concat(Observable.just(1,2,3), Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    public void accept(@NonNull Integer integer) {
                        rxOperatorsText.append("concat : ").append(integer).append("\n");
                        logger.info("concat : "+ integer + "\n" );
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * 它可以把一个发射器 Observable 通过某种方法转换为多个 Observables，
     * 然后再把这些分散的 Observables装进一个单一的发射器 Observable。
     * 但有个需要注意的是，flatMap 并不能保证事件的顺序
     */
    @Test void testFlatMap() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            public void subscribe(@NonNull ObservableEmitter<Integer> e) {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).flatMap(new Function<Integer, ObservableSource<String>>() {
            public ObservableSource<String> apply(@NonNull Integer integer) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    public void accept(@NonNull String s) {
                        logger.info("flatMap : accept : " + s + "\n");
                        rxOperatorsText.append("flatMap : accept : " + s + "\n");
                    }
                });

        print(rxOperatorsText);
    }

    /**
     * concatMap 和 flatMap相比就是concatMap保证了顺序
     */
    @Test void testConcatMap() {
        StringBuffer rxOperatorsText = new StringBuffer();

        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) {
                e.onNext(1);
                e.onNext(2);
                e.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(@NonNull Integer integer) {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                int delayTime = (int) (1 + Math.random() * 10);
                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
            }
        })
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        logger.info("flatMap : accept : " + s + "\n");
                        rxOperatorsText.append("flatMap : accept : ").append(s).append("\n");
                    }
                });

        print(rxOperatorsText);
    }

    /**
     * distinct 去重
     */
    @Test void testDistinct() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just(1, 1, 1, 2, 2, 3, 4, 5)
                .distinct() //去重
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) {
                        rxOperatorsText.append("distinct : ").append(integer).append("\n");
                        logger.info("distinct : " + integer + "\n");
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * filter 过滤掉不符合条件的值
     */
    @Test void testFilter() {
        StringBuffer rxOperatorsText = new StringBuffer();

        Observable.just(1, 20, 65, -5, 7, 19)
                .filter(new Predicate<Integer>() { //过滤
                    @Override
                    public boolean test(@NonNull Integer integer) {
                        return integer >= 10;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) {
                rxOperatorsText.append("filter : ").append(integer).append("\n");
                logger.info("filter : " + integer + "\n");
            }
        });

        print(rxOperatorsText);
    }

    /**
     * buffer(count, skip)
     * count: 合并的数据数量
     * skip: 跳过的数据数量
     * 这两个是单独计算的
     */
    @Test void testBuffer() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .buffer(2, 3)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) {
                        rxOperatorsText.append("buffer size : " + integers.size() + "\n");
                        logger.info("buffer size : " + integers.size() + "\n");
                        rxOperatorsText.append("buffer value : ");
                        logger.info("buffer value : " );
                        for (Integer i : integers) {
                            rxOperatorsText.append(i);
                            logger.info(i + "");
                        }
                        rxOperatorsText.append("\n");
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * 定时器，没有 **AndroidSchedulers** 暂时没有调试
     */
    @Test void testTimer() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        rxOperatorsText.append("timer start : " + DateUtils.nowTime() + "\n");
        logger.info("timer start : " + DateUtils.nowTime() + "\n");
        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()) // timer 默认在新线程，所以需要切换回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        rxOperatorsText.append("timer :" + aLong + " at " + DateUtils.nowTime() + "\n");
                        logger.info("timer :" + aLong + " at " + DateUtils.nowTime() + "\n");
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * 定时器，没有 **AndroidSchedulers** 暂时没有调试
     */
    @Test void testInterval() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        rxOperatorsText.append("interval start : ").append(DateUtils.nowTime()).append("\n");
        logger.info("interval start : " + DateUtils.nowTime() + "\n");
        Observable.interval(3,2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) {
                        rxOperatorsText.append("interval :" + aLong + " at " + DateUtils.nowTime() + "\n");
                        logger.info("interval :" + aLong + " at " + DateUtils.nowTime() + "\n");
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * 让订阅者在接收到数据之前干点有意思的事情。假如我们在获取到数据之前想先保存一下它，无疑我们可以这样实现。
     */
    @Test void testDoOnNext() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just(1, 2, 3, 4)
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) {
                        rxOperatorsText.append("doOnNext 保存 ").append(integer).append("成功").append("\n");
                        logger.info("doOnNext 保存 " + integer + "成功" + "\n");
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) {
                rxOperatorsText.append("doOnNext :" + integer + "\n");
                logger.info("doOnNext :" + integer + "\n");
            }
        });
        print(rxOperatorsText);
    }

    /**
     * skip接受一个 long 型参数 count ，代表跳过 count 个数目开始接收
     */
    @Test void testSkip() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just(1,2,3,4,5)
                .skip(2) //跳过2个
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) {
                        rxOperatorsText.append("skip : ").append(integer).append("\n");
                        logger.info("skip : "+integer + "\n");
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * take，接受一个 long 型参数 count ，代表至多接收 count 个数据。
     */
    @Test void testTake() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Flowable.fromArray(1,2,3,4,5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) {
                        rxOperatorsText.append("take : ").append(integer).append("\n");
                        logger.info("accept: take : "+integer + "\n" );
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * just发射器，就是一个简单的发射器依次调用 onNext() 方法。
     */
    @Test void tastJust() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just("1", "2", "3")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        rxOperatorsText.append("accept : onNext : " + s + "\n");
                        logger.info("accept : onNext : " + s + "\n" );
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * Single 只会接收一个参数，而 SingleObserver 只会调用 onError() 或者 onSuccess()。
     */
    @Test void testSingle() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        rxOperatorsText.append("single : onSuccess : ").append(integer).append("\n");
                        logger.info("single : onSuccess : "+integer+"\n" );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        rxOperatorsText.append("single : onError : ").append(e.getMessage()).append("\n");
                        logger.info("single : onError : "+e.getMessage()+"\n");
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * 去除发送频率过快的项
     */
    @Test void testDebounce() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                // send events with simulated time wait
                emitter.onNext(1); // skip
                Thread.sleep(400);
                emitter.onNext(2); // deliver
                Thread.sleep(505);
                emitter.onNext(3); // skip
                Thread.sleep(100);
                emitter.onNext(4); // deliver
                Thread.sleep(605);
                emitter.onNext(5); // deliver
                Thread.sleep(510);
                emitter.onComplete();
            }
        }).debounce(500, TimeUnit.MILLISECONDS) //过滤掉间隔小于 500ms 的数据
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        rxOperatorsText.append("debounce :").append(integer).append("\n");
                        logger.info("debounce :" + integer + "\n");
                    }
                });

        print(rxOperatorsText);

    }

    /**
     * 每次订阅都会创建一个新的 Observable，并且如果没有被订阅，就不会产生新的 Observable。
     */
    @Test void testDefer() {

        final StringBuffer rxOperatorsText = new StringBuffer();

        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });


        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                rxOperatorsText.append("defer : ").append(integer).append("\n");
                logger.info("defer : " + integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                rxOperatorsText.append("defer : onError : ").append(e.getMessage()).append("\n");
                logger.info("defer : onError : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                rxOperatorsText.append("defer : onComplete\n");
                logger.info("defer : onComplete\n");
            }
        });

        print(rxOperatorsText);
    }

    /**
     * last 操作符仅取出可观察到的最后一个值，或者是满足某些条件的最后一项。
     */
    @Test void testLast() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just(1, 2, 3, 4, 5, 6)
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) {
                        rxOperatorsText.append("last : " + integer + "\n");
                        logger.info("last : " + integer + "\n");
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * merge 的作用是把多个 Observable 结合起来，
     * 接受可变参数，也支持迭代器集合。注意它和 concat 的区别在于，不用等到 发射器 A 发送完所有的事件再进行发射器 B 的发送。
     */
    @Test void testMerge() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.merge(Observable.just(1, 2), Observable.just(3, 4, 5))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) {
                        rxOperatorsText.append("merge :").append(integer).append("\n");
                        logger.info("accept: merge :" + integer + "\n" );
                    }
                });
        print(rxOperatorsText);
    }

    /**
     * reduce 操作符每次用一个方法处理一个值，可以有一个 seed 作为初始值。
     */
    @Test void testReduce() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just(1, 2, 3)
                .reduce(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                rxOperatorsText.append("reduce : " + integer + "\n");
                logger.info("accept: reduce : " + integer + "\n");
            }
        });
        print(rxOperatorsText);
    }

    /**
     * scan 操作符作用和上面的 reduce 一致，唯一区别是 reduce 是个只追求结果的坏人，而 scan 会始终如一地把每一个步骤都输出。
     */
    @Test void testScan() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        Observable.just(1, 2, 3)
                .scan(new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) {
                        return integer + integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) {
                rxOperatorsText.append("scan ").append(integer).append("\n");
                logger.info("accept: scan " + integer + "\n");
            }
        });
        print(rxOperatorsText);
    }

    /**
     * 按照实际划分窗口，将数据发送给不同的 Observable
     */
    @Test void testWindow() {
        final StringBuffer rxOperatorsText = new StringBuffer();
        rxOperatorsText.append("window\n");
        logger.info("window\n");
        Observable.interval(1, TimeUnit.SECONDS) // 间隔一秒发一次
                .take(15) // 最多接收15个
                .window(3, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Observable<Long>>() {
                    @Override
                    public void accept(@NonNull Observable<Long> longObservable) {
                        rxOperatorsText.append("Sub Divide begin...\n");
                        logger.info("Sub Divide begin...\n");
                        longObservable
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Consumer<Long>() {
                                    @Override
                                    public void accept(@NonNull Long aLong) {
                                        rxOperatorsText.append("Next:").append(aLong).append("\n");
                                        logger.info("Next:" + aLong + "\n");
                                    }
                                });
                    }
                });
        print(rxOperatorsText);
    }

    private void print(StringBuffer rxOperatorsText) {
        logger.info("--------");
        System.out.println(rxOperatorsText);
    }

}
