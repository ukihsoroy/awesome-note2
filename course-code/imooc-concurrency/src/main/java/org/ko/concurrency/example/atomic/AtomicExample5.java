package org.ko.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.ko.concurrency.annoations.ThreadSafe;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@ThreadSafe
public class AtomicExample5 {

    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class, "count");


    /**
     * 必须是volatile 并且不是static的
     */
    @Getter
    public volatile Integer count;

    public static void main(String[] args) {
        AtomicExample5 example5 = new AtomicExample5();
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success: {}", example5.getCount());
        }
        if (updater.compareAndSet(example5, 100, 120)) {
            log.info("update success: {}", example5.getCount());
        } else {
            log.info("update failed: {}", example5.getCount());
        }
    }
}
