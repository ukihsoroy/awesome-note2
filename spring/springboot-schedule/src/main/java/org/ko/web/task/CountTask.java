package org.ko.web.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class CountTask {

    private static final Logger _Logger = LoggerFactory.getLogger(CountTask.class);

    //原子类
    private static AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedRate = 1000)    //单位：毫秒
    public void count () {
        _Logger.info("count: {}", count.getAndAdd(1));
    }
}
