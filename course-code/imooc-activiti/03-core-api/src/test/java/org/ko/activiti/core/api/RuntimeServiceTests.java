package org.ko.activiti.core.api;

import com.google.common.collect.Maps;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.*;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * description: 流程运行控制服务 <br>
 * date: 2020/2/13 16:27 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class RuntimeServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(RuntimeServiceTests.class);

    //空的，使用默认的流程配置文件
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 通过流程key启动流程
     * 流程key是不变的
     */
    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testStartProcessByKey() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);
        logger.info("processInstance = {}", processInstance);
    }

    /**
     * 通过id启动流程
     * 流程id每次部署都会发生变化
     * 需要通过repositoryService去获取流程ID启动流程
     */
    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testStartProcessById() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessDefinition processDefinition = activitiRule.getRepositoryService().createProcessDefinitionQuery().singleResult();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value1");
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), variables);
        logger.info("processInstance = {}", processInstance);
    }

    /**
     * 通过流程实例构建类启动流程
     */
    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testProcessInstanceBuilder() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value1");
        ProcessInstanceBuilder processInstanceBuilder = runtimeService.createProcessInstanceBuilder();
        ProcessInstance businessKey001 = processInstanceBuilder
                .businessKey("businessKey001")
                .processDefinitionKey("my-process")
                .variables(variables)
                .start();

        logger.info("processInstance = {}", businessKey001);
    }

    /**
     * 测试修改参数
     */
    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testVariables() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value1");
        variables.put("key2", "value2");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);
        logger.info("processInstance = {}", processInstance);

        //添加及修改变量
        runtimeService.setVariable(processInstance.getId(), "key1", "change1");
        runtimeService.setVariable(processInstance.getId(), "key3", "value3");
        Map<String, Object> map = runtimeService.getVariables(processInstance.getId());

        logger.info("map = {}", map);
    }

    /**
     * 查询正在运行中的流程
     */
    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testProcessInstanceQuery() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);

        logger.info("processInstance = {}", processInstance);

        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
        //通过流程id查询
        ProcessInstance processInstance1 = processInstanceQuery.processDefinitionId(processInstance.getId()).singleResult();
    }

    /**
     * 查询流程执行对象
     */
    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testExecutionQuery() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        Map<String, Object> variables = Maps.newHashMap();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);

        logger.info("processInstance = {}", processInstance);

        //查询流程执行对象
        ExecutionQuery executionQuery = runtimeService.createExecutionQuery();
        List<Execution> executions = executionQuery.list();
        for (Execution execution : executions) {
            logger.info("execution = {}", execution);
        }
    }

    @Deployment(resources = {"my-process-trigger.bpmn20.xml"})
    @Test public void testTrigger() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");

        Execution someTask = runtimeService.createExecutionQuery()
                .activityId("someTask")
                .singleResult();

        logger.info("execution = {}", someTask);

        //触发执行
        runtimeService.trigger(someTask.getId());

        //再次查看
        someTask = runtimeService.createExecutionQuery()
                .activityId("someTask")
                .singleResult();

        logger.info("execution = {}", someTask);

    }

    @Deployment(resources = {"my-process-signal-received.bpmn20.xml"})
    @Test public void testSignalEventReceived() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");

        Execution execution = runtimeService.createExecutionQuery()
                .signalEventSubscriptionName("my-signal")
                .singleResult();
        logger.info("execution = {}", execution);

        //触发信号和流程并没有任何关系
        runtimeService.signalEventReceived("my-signal");

        execution = runtimeService.createExecutionQuery()
                .signalEventSubscriptionName("my-signal")
                .singleResult();
        logger.info("execution = {}", execution);
    }

    @Deployment(resources = {"my-process-message-received.bpmn20.xml"})
    @Test public void testMessageEventReceived() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process");

        Execution execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("my-message")
                .singleResult();
        logger.info("execution = {}", execution);

        //触发信号和流程并没有任何关系
        runtimeService.messageEventReceived("my-message", execution.getId());

        execution = runtimeService.createExecutionQuery()
                .messageEventSubscriptionName("my-message")
                .singleResult();
        logger.info("execution = {}", execution);
    }

    /**
     * 基于message去启动流程实例
     */
    @Deployment(resources = {"my-process-message.bpmn20.xml"})
    @Test public void testStartProcessByMessage() {
        RuntimeService runtimeService = activitiRule.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByMessage("my-message");
        logger.info("processInstance = {}", processInstance);
    }
}
