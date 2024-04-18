package org.ko.activiti.config;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * activiti和spring集成配置
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:activiti-context.xml"})
public class ConfigSpringTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    /**
     * 1. 不写参数，使用默认的
     * 2. activiti_mdc.cfg.xml，添加了mdc的拦截器，打印树
     */
    @Rule
    @Autowired
    public ActivitiRule activitiRule;

    @Autowired
    public RuntimeService runtimeService;

    @Autowired
    public TaskService taskService;

    @Test
    //辅助单元测试的注解
    @Deployment(resources = {"org.ko.activiti/my-process_spring.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");
        assertNotNull(processInstance);
        Task task = taskService.createTaskQuery().singleResult();
        taskService.complete(task.getId());
    }


}
