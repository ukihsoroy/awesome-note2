package org.ko.concurrency.synchroniz;

import java.util.concurrent.TimeUnit;

/**
 * 静态方法同步 被锁住的是当前的Class对象
 *
 */
public class T3ClassLock {

    public static void main(String[] args) {
        //同时间只能有一个线程访问
        for(int i=0;i<50;i++){
            Thread t1 = new Thread(new Sale(5));
            Thread t2 = new Thread(new Producted(5));
            t1.start();
            t2.start();
        }
    }
}

class Shop {

    static int a = 40;

    synchronized static void shopping(int b){
        a -= b;
        System.out.println("售出 " + b + " 一幅字画," + "还剩 " + a + " 幅!");
    }

    synchronized static void factory(int c){
        a += c;
        System.out.println("仓库还有 " + a + " 幅画!");
    }
}

class Sale implements Runnable{

    int b = 0;

    public Sale(int b){
        this.b = b;
    }

    @Override
    public void run() {
        if(b < 0){
            //返回线程的上次的中断状态，并清除中断状态
            Thread.interrupted();
        }
        Shop.shopping(b);
        try {
            TimeUnit.SECONDS.sleep(1);
            Shop.factory(b - 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Producted implements Runnable{

    int b = 0;

    public Producted(int b){
        this.b = b;
    }

    @Override
    public void run() {
        Shop.factory(b);
        try {
            TimeUnit.SECONDS.sleep(1);
            Shop.shopping(b - 5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}