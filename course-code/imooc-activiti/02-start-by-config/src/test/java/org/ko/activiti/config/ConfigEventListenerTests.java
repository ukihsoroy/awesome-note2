package org.ko.activiti.config;

import org.activiti.engine.delegate.event.ActivitiEventType;
import org.activiti.engine.delegate.event.impl.ActivitiEventImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.ko.activiti.event.CustomEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * event listener测试
 */
public class ConfigEventListenerTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    /**
     * 1. 不写参数，使用默认的
     * 2. activiti_mdc.cfg.xml，添加了mdc的拦截器，打印树
     * 3. 基于mdc配置文件，添加了event log配置
     */
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_eventlistener.cfg.xml");

    @Test
    //辅助单元测试的注解
    @Deployment(resources = {"org.ko.activiti/my-process_event.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());

        //使用代码的方式配置监听，也可以使用配置文件
        activitiRule.getRuntimeService().addEventListener(new CustomEventListener());
        activitiRule.getRuntimeService().dispatchEvent(new ActivitiEventImpl(ActivitiEventType.CUSTOM));

    }

}
