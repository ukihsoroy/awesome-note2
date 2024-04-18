package org.ko.concurrency.example.lock;

import lombok.extern.slf4j.Slf4j;
import org.ko.concurrency.annoations.ThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

@Slf4j
@ThreadSafe
public class LockExample6 {

    /**
     * Condition
     * @param args
     */
    public static void main(String[] args) {

    }

}
