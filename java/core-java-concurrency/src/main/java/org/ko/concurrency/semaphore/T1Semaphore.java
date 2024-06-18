package org.ko.concurrency.semaphore;

import org.ko.concurrency.utils.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 线程共享锁
 * 当只是读的时候是允许线程获取锁资源
 * 信号量Semaphore是一个控制访问多个共享资源的计数器，它本质上是一个“共享锁”
 */
public class T1Semaphore {

    public static void main(String[] args) {
        //共享锁
        final Semaphore semaphore = new Semaphore(5);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            int j = 0;
            executorService.submit(new Car("car"+(j++),semaphore),"Thread"+(j++));
            //new Thread(new A("car"+(j++),semaphore),"Thread"+(j++)).start();
            if(i == 5){
                try {
                    Thread.sleep(1000);
                    System.out.println("最后还有"+semaphore.availablePermits()+"个许可可用");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("----最后还有"+semaphore.availablePermits()+"个许可可用");

        ThreadUtils.destory(executorService);
    }

}
class Car implements Runnable {

    String carName;

    private Semaphore semaphore;

    public Car(String carName, Semaphore semaphore){
        this.carName = carName;
        this.semaphore = semaphore;
    }

    public void getWay(){
        System.out.println("this car is get the way" + Thread.currentThread().getName());
    }

    public void run() {
        try {
            if(semaphore.availablePermits() > 0){
                //获取访问许可
                semaphore.acquire();
                getWay();
                //释放访问许可
                semaphore.release();
            }else{
                System.out.println("请等待========");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
