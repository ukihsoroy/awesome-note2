package org.ko.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description: ActivitiTests <br>
 * date: 2020/2/18 21:40 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ActivitiTests {

    private static final Logger logger = LoggerFactory.getLogger(ActivitiTests.class);

    @Autowired
    private RuntimeService runtimeService;
    
    @Test public void contextLoads() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");

        logger.info("processInstance = {}", processInstance);
    }
}
