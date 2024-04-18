package org.ko.activiti.config;

import com.google.common.collect.Maps;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricDetail;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * MDC测试
 */
public class ConfigHistoryLevelTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    /**
     * 1. 不写参数，使用默认的
     * 2. activiti_mdc.cfg.xml，添加了mdc的拦截器，打印树
     * 3. 基于mdc配置文件，配置了activiti历史记录的配置
     */
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_history.cfg.xml");

    @Test
    //辅助单元测试的注解
    @Deployment(resources = {"org.ko.activiti/my-process_history.bpmn20.xml"})
    public void test() {

        //启动流程，完成task
        startProcessInstance();

        //修改变量
        changeVariable();

        //提交表单 task
        submitTaskFormData();

        //输入历史活动
        showHistoryActivity();

        //输出历史变量
        showHistoryVariable();

        //输出历史用户任务
        showHistoryTask();

        //输出历史表单
        showHistoryForm();

        //输出历史详情
        showHistoryDetail();
    }

    private void showHistoryDetail() {
        List<HistoricDetail> historicDetails = activitiRule.getHistoryService()
                .createHistoricDetailQuery().list();
        for (HistoricDetail historicDetail : historicDetails) {
            logger.info("historicDetail = {}", toString(historicDetail));
        }
        logger.info("historicDetails.size = {}", historicDetails.size());
    }

    private void showHistoryForm() {
        List<HistoricDetail> historicDetailsFrom = activitiRule.getHistoryService()
                .createHistoricDetailQuery().formProperties().list();

        for (HistoricDetail historicDetailFrom : historicDetailsFrom) {
            logger.info("historicDetailFrom = {}", toString(historicDetailFrom));
        }
        logger.info("historicDetailsFrom.size = {}", historicDetailsFrom.size());
    }

    private void showHistoryTask() {
        List<HistoricTaskInstance> historicTaskInstances = activitiRule.getHistoryService()
                .createNativeHistoricTaskInstanceQuery().list();

        for (HistoricTaskInstance historicTaskInstance : historicTaskInstances) {
            logger.info("historicTaskInstance = {}", historicTaskInstance);
        }
        logger.info("historicTaskInstances.size = {}", historicTaskInstances.size());
    }

    private void showHistoryVariable() {
        List<HistoricVariableInstance> historicVariableInstances = activitiRule.getHistoryService()
                .createHistoricVariableInstanceQuery().list();
        for (HistoricVariableInstance historicVariableInstance : historicVariableInstances) {
            logger.info("historicVariableInstance = {}", historicVariableInstance);
        }
        logger.info("historicVariableInstances.size = {}", historicVariableInstances.size());
    }

    private void showHistoryActivity() {
        List<HistoricActivityInstance> historicActivityInstances = activitiRule.getHistoryService()
                .createHistoricActivityInstanceQuery().list();

        for (HistoricActivityInstance historicActivityInstance : historicActivityInstances) {
            logger.info("historicActivityInstance = {}", historicActivityInstance);
        }

        logger.info("historicActivityInstances.size = {}", historicActivityInstances.size());
    }

    private void submitTaskFormData() {
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        Map<String, String> properties = Maps.newHashMap();
        properties.put("formKey1", "valuef1");
        properties.put("formKey2", "valuef2");
        activitiRule.getFormService().submitTaskFormData(task.getId(), properties);
    }

    private void changeVariable() {
        List<Execution> executions = activitiRule.getRuntimeService()
                .createExecutionQuery().list();

        for (Execution execution : executions) {
            logger.info("execution = {}", execution);
        }

        logger.info("execution.size = {}", executions.size());

        String id = executions.iterator().next().getId();
        activitiRule.getRuntimeService().setVariable(id, "keyStart1", "value1_");
    }

    private void startProcessInstance() {
        Map<String, Object> params = Maps.newHashMap();
        params.put("keyStart1", "value1");
        params.put("keyStart2", "value2");
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process", params);
    }

    static String toString(HistoricDetail historicDetail) {
        return ToStringBuilder.reflectionToString(historicDetail, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
