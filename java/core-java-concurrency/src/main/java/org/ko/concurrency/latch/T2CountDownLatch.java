package org.ko.concurrency.latch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T2CountDownLatch {

    private static Integer count = 500;

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(50);

        for (int i = 0; i < 50; i++) {
            new Thread(new Person(latch)).start();
        }
        latch.countDown();

    }

    static class Person implements Runnable {

        private CountDownLatch latch;

        public Person (CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            buy();
        }

        private void buy () {
            lock.lock();
            try {
                if (count > 0) {
                    count = count - 5;
                }
                System.out.println("买了5件, 还剩" + count + "件!");
            } finally {
                lock.unlock();
            }
        }

    }
}
