package org.ko.web.async;


import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController
public class AsyncController {

    @Autowired private MockQueue mockQueue;

    @Autowired private DeferredResultHolder deferredResultHolder;

    private Logger _LOGGER = LoggerFactory.getLogger(AsyncController.class);

    /**
     * 服务器吞吐量会有很大的提升-前端感觉不到区别, tomcat吞吐量会提高
     * @return
     * @throws InterruptedException
     */
    @GetMapping("order1")
    public Callable<String> order1 () throws InterruptedException {
        _LOGGER.info("主线程开始");
        Callable<String> callable = () -> {
            _LOGGER.info("副线程启动");
            TimeUnit.SECONDS.sleep(1);
            _LOGGER.info("副线程返回");
            return "success";
        };
        _LOGGER.info("主线程返回");
        return callable;
    }

    @GetMapping("order2")
    public DeferredResult<String> order () throws InterruptedException {
        _LOGGER.info("主线程开始");
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);
        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);
        return result;
    }



}
