package org.ko.concurrency.lock;

import java.sql.Time;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.List;
/**
 * 锁降级
 * 从一个写入锁 降级为读取锁
 * 读取锁永远也不可能晋级成写入锁
 */
public class T5ReadWriteLockDegrade {

    Object data;

    volatile boolean cacheValid;

    ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // 在获得写锁之前必须释放读锁
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            // Recheck state because another thread might have acquired
            //   write lock and changed state before we did.
            if (!cacheValid) {
                data = "This is...";
                cacheValid = true;
            }
            //通过在释放写锁之前获得读锁来降级
            rwl.readLock().lock();
            rwl.writeLock().unlock(); // 解锁写锁，但是任然持有读锁
        }

        System.out.println(data);
        rwl.readLock().unlock();
    }

    public static void main(String[] args) {
        T5ReadWriteLockDegrade t5 = new T5ReadWriteLockDegrade();
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Runnable> runnables = Arrays.asList(
                () -> t5.processCachedData(),
                () -> t5.processCachedData(),
                () -> t5.processCachedData(),
                () -> t5.processCachedData(),
                () -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    t5.processCachedData();
                }
        );

        runnables.forEach(runnable -> executor.submit(runnable));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }


}