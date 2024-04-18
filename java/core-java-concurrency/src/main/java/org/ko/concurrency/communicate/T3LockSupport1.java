package org.ko.concurrency.communicate;

import java.util.concurrent.locks.LockSupport;

/**
 * 线程阻塞
 */
public class T3LockSupport1 {
    public static Object u = new Object();
    static ChangeObjectThread t1 = new ChangeObjectThread("tn");
    static ChangeObjectThread t2 = new ChangeObjectThread("t2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super.setName(name);
        }

        public void run() {
            synchronized (u) {
                System.out.println("in" + getName());
                //park获得该线程 然后阻塞该线程
                LockSupport.park();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        t1.start();
        Thread.sleep(2000);
        t2.start();
        //unpark通过传递参数来进行唤醒
        LockSupport.unpark(t1);
        LockSupport.unpark(t2);
        t1.join();
        t2.join();
    }
}
