package org.ko.concurrency.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T2ThreadLock {


    private int j;

    private Lock lock = new ReentrantLock();

    public static void main (String[] args) {

        T2ThreadLock tt = new T2ThreadLock();

        for (int i=0; i<2; i++){
            new Thread(tt.new Adder()).start();
            new Thread(tt.new Subtractor()).start();
        }

    }

    private class Subtractor implements Runnable{
        public void run(){
            while (true){
                lock.lock();
                try {
                    System.out.println("j--=" + j--);
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    private class Adder implements Runnable {
        public void run() {
            while (true) {
                lock.lock();
                try {
                    System.out.println("j++" + j++);
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
