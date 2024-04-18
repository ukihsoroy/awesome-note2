package org.ko.activiti.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: 测试 Activiti 流程中的 spring bean 配置 <br>
 * date: 2020/2/11 13:36 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class SimpleEntity {

    private static final Logger logger = LoggerFactory.getLogger(SimpleEntity.class);

    public void sayHello() {
        logger.info("say hello.");
    }
}
