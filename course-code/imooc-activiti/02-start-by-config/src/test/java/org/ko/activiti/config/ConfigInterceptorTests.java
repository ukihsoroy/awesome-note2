package org.ko.activiti.config;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拦截器测试
 */
public class ConfigInterceptorTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    /**
     * 1. 不写参数，使用默认的
     * 2. activiti_interceptor.cfg.xml，添加了mdc的拦截器，打印树
     * 3. 拦截器配置
     */
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_interceptor.cfg.xml");

    @Test
    //辅助单元测试的注解
    @Deployment(resources = {"org.ko.activiti/my-process_event.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());
    }

}
