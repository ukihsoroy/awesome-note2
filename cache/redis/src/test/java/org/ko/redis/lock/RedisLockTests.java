package org.ko.redis.lock;

import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RedisLockTests {

    //单线程测试
    @Test void test1() {
        Jedis jedis = new Jedis("106.12.110.82", 6379);
        jedis.auth("tiger");
        RedisReentrantLock lock = new RedisReentrantLock(jedis);
        System.out.println(lock.lock("codehole"));
        System.out.println(lock.lock("codehole"));
        System.out.println(lock.unlock("codehole"));
        System.out.println(lock.unlock("codehole"));
    }

    //多线程测试
    @Test void test2() throws InterruptedException {
        Jedis jedis = new Jedis("106.12.110.82", 6379);
        jedis.auth("tiger");
        RedisReentrantLock lock = new RedisReentrantLock(jedis);

        List<Runnable> runnables = Arrays.asList(
                () -> getResource(1, lock),
                () -> getResource(2, lock),
                () -> getResource(3, lock),
                () -> getResource(4, lock)
        );

        ExecutorService service = Executors.newSingleThreadExecutor();

        runnables.forEach(service::submit);

        TimeUnit.SECONDS.sleep(20);

        service.shutdown();
    }

    //超时测试
    @Test void test3() throws InterruptedException {
        Jedis jedis = new Jedis("106.12.110.82", 6379);
        jedis.auth("tiger");
        RedisReentrantLock lock = new RedisReentrantLock(jedis);

        List<Runnable> runnables = Arrays.asList(
                () -> getResource1(1, lock),
                () -> getResource1(3, lock),
                () -> getResource1(2, lock),
                () -> getResource1(4, lock)
        );

        ExecutorService service = Executors.newSingleThreadExecutor();

        runnables.forEach(service::submit);

        TimeUnit.SECONDS.sleep(200);

        service.shutdown();
    }

    public void getResource(int no, RedisReentrantLock lock) {
        String key = "reentrant";
        System.out.println("goto " + no);
        boolean isLock = lock.lock(key);
        if (isLock) {
            System.out.println(no + " get lock");
            lock.unlock(key);
        }
    }

    public void getResource1(int no, RedisReentrantLock lock) {
        String key = "reentrant";
        System.out.println("goto " + no);
        boolean isLock = lock.lock(key);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (isLock) {
            System.out.println(no + " get lock");
        }
    }
}
