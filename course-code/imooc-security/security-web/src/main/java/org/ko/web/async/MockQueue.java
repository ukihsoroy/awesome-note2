package org.ko.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 模拟消息队列
 */
@Component
public class MockQueue {

    private static final Logger _LOGGER = LoggerFactory.getLogger(MockQueue.class);

    /**
     * 下单消息
     */
    private String placeOrder;

    /**
     * 订单完成消息
     */
    private String completeOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        _LOGGER.info("接到下单请求");
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.completeOrder = placeOrder;
            _LOGGER.info("下单请求处理完毕");
        }).start();
    }

    public String getCompleteOrder() {
        return completeOrder;
    }

    public void setCompleteOrder(String completeOrder) {
        this.completeOrder = completeOrder;
    }
}
