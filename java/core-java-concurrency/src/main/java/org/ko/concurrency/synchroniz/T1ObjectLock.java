package org.ko.concurrency.synchroniz;


/**
 * 为对象加锁
 * synchronized 的限制
 *  1) 它无法中断一个正在等候获得锁的线程
 *  2) 也无法通过投票得到锁，如果不想等下去，也就没法得到锁。
 *  3) 同步还要求锁的释放只能在与获得锁所在的堆栈帧相同的堆栈帧中进行
 */
public class T1ObjectLock implements Runnable {

    //被锁的静态对象
    static T1ObjectLock instance = new T1ObjectLock() ;

    //保证i的值
    static int i = 0;

    @Override
    public void run() {
        for (int j=0 ; j<100000; j++) {
            //对象锁
            synchronized (instance) {
                //这里i++不是原子性的 有取值和赋值操作
                i++;
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        tn();
        t2();
    }

    /**
     * 线程访问的是同一个对象 所以有安全性
     * @throws Exception
     */
    public static void t1 () throws Exception {
        Thread t1=new Thread(instance);
        Thread t2=new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }

    /**
     * 这里线程访问的并不是一个对象 安全性没得到保证
     * @throws Exception
     */
    public static void t2 () throws Exception {
        Thread t1=new Thread(new T1ObjectLock());
        Thread t2=new Thread(new T1ObjectLock());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);

    }
}

