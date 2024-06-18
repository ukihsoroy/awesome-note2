package org.ko.activiti.core.api;

import com.google.common.collect.Maps;
import org.activiti.engine.HistoryService;
import org.activiti.engine.history.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceBuilder;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * description: 历史管理服务 <br>
 * date: 2020/2/15 18:46 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class HistoryServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(HistoryServiceTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_history.cfg.xml");

    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testHistory() {
        HistoryService historyService = activitiRule.getHistoryService();

        ProcessInstanceBuilder processInstanceBuilder = activitiRule.getRuntimeService().createProcessInstanceBuilder();

        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key0", "value0");
        variables.put("key1", "value1");
        variables.put("key2", "value2");

        //临时变量不会存到历史数据里面
        Map<String, Object> transientVariables = Maps.newHashMap();
        transientVariables.put("tkey1", "tvalue1");

        ProcessInstance processInstance = processInstanceBuilder.processDefinitionKey("my-process")
                .variables(variables)
                .transientVariables(transientVariables)
                .start();

        //修改变量
        activitiRule.getRuntimeService().setVariable(processInstance.getId(), "key1", "value1_1");

        Task task = activitiRule.getTaskService()
                .createTaskQuery()
                .processInstanceId(processInstance.getId())
                .singleResult();

//        activitiRule.getTaskService().complete(task.getId());

        //通过表单存储的数据，也会加入到historyDetail里面
        Map<String, String> properties = Maps.newHashMap();
        properties.put("fKey1", "fValue1");
        properties.put("key2", "value2_2");
        activitiRule.getFormService().submitTaskFormData(task.getId(), properties);

        //通过historyService查询

        //历史流程实例实体类
        List<HistoricProcessInstance> historicProcessInstances = historyService.createHistoricProcessInstanceQuery().list();
        for (HistoricProcessInstance historicProcessInstance : historicProcessInstances) {
            logger.info("historicProcessInstance = {}", historicProcessInstance);
        }

        //单个活动节点执行的信息
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery().list();
        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            logger.info("historicActivityInstance = {}", historicActivityInstance);
        }

        //用户任务实例的信息
        List<HistoricTaskInstance> historicTaskInstances = historyService.createHistoricTaskInstanceQuery().list();
        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            logger.info("historicTaskInstance = {}", historicTaskInstance);
        }

        //流程或任务变量值的实体
        List<HistoricVariableInstance> historicVariableInstances = historyService.createHistoricVariableInstanceQuery().list();
        for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
            logger.info("historicVariableInstance = {}", historicVariableInstance);
        }

        //历史流程活动任务详细信息
        List<HistoricDetail> historicDetails = historyService.createHistoricDetailQuery().list();
        for (HistoricDetail historicDetail : historicDetails) {
            logger.info("historicDetail = {}", historicDetail);
        }

        //查看一个流程实例的全部历史数据
        ProcessInstanceHistoryLog processInstanceHistoryLog = historyService.createProcessInstanceHistoryLogQuery(processInstance.getId())
                .includeActivities()
                .includeComments()
                .includeVariables()
                .includeTasks()
                .includeFormProperties()
                .includeVariableUpdates()
                .singleResult();

        List<HistoricData> historicData = processInstanceHistoryLog.getHistoricData();
        for (HistoricData historicDatum : historicData) {
            logger.info("historicDatum = {}", historicDatum);
        }

        //删除后应该为null
        historyService.deleteHistoricProcessInstance(processInstance.getId());

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();

        logger.info("historicProcessInstance = {}", historicProcessInstance);

    }

}
