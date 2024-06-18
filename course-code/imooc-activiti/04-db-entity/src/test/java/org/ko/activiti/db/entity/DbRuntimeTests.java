package org.ko.activiti.db.entity;

import com.google.common.collect.Maps;
import org.activiti.engine.IdentityService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * description: 通用数据库测试 <br>
 * date: 2020/2/16 19:34 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class DbRuntimeTests {

    private static final Logger logger = LoggerFactory.getLogger(DbRuntimeTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    /**
     * 用户关系
     */
    @Test public void testRuntime() {
        activitiRule.getRepositoryService()
                .createDeployment()
                .name("二次审批流程")
                .addClasspathResource("second_approve.bpmn20.xml")
                .deploy();

        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value1");
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("second_approve", variables);

    }

    @Test public void testSetOwner() {
        TaskService taskService = activitiRule.getTaskService();
        Task second_approve = taskService.createTaskQuery().processDefinitionKey("second_approve").singleResult();
        taskService.setOwner(second_approve.getId(), "user1");
    }

    //对应的流程定义，没有流程实例的id
    @Test public void testDeployMessage() {
        activitiRule.getRepositoryService().createDeployment()
                .addClasspathResource("my-process-message.bpmn20.xml")
                .deploy();
    }

    //对应的流程实例，会有流程实例对应的id
    @Test public void testDeployMessageReceived() {
        activitiRule.getRepositoryService().createDeployment()
                .addClasspathResource("my-process-message-received.bpmn20.xml")
                .deploy();

        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
    }

    //定时任务
    @Test public void testJob() {
        activitiRule.getRepositoryService().createDeployment()
                .addClasspathResource("my-process-job.bpmn20.xml")
                .deploy();

        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
