package org.ko.web.service.task;

import org.ko.web.service.print.SimpleEndpoint;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

//@Component
public class SendMessageTask {

    /**
     * 线程安全基本数据
     */
    private static AtomicInteger COUNT = new AtomicInteger();

    @Scheduled(fixedRate = 1000)
    public void sendMessage () {
        SimpleEndpoint.sendUsers("发送消息" + COUNT.addAndGet(1));
    }

}
