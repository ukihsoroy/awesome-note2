package org.ko.activiti.core.api;

import com.google.common.collect.Maps;
import org.activiti.engine.FormService;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.StartFormData;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * description: 表单管理服务 <br>
 * date: 2020/2/15 15:39 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class FormServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(FormServiceTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Deployment(resources = {"my-process-form.bpmn20.xml"})
    @Test public void testFormService() {
        FormService formService = activitiRule.getFormService();

        ProcessDefinition processDefinition = activitiRule
                .getRepositoryService()
                .createProcessDefinitionQuery()
                .singleResult();

        //获取启动formStartKey
        String startFormKey = formService.getStartFormKey(processDefinition.getId());
        logger.info("startFormKey = {}", startFormKey);

        StartFormData startFormData = formService.getStartFormData(processDefinition.getId());
        List<FormProperty> formProperties = startFormData.getFormProperties();
        for (FormProperty formProperty : formProperties) {
            logger.info("startFormProperty = {}", ToStringBuilder.reflectionToString(formProperty));
        }

        Map<String, String> properties = Maps.newHashMap();
        properties.put("message", "my test message!");
        ProcessInstance processInstance = formService.submitStartFormData(processDefinition.getId(), properties);

        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();

        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties1 = taskFormData.getFormProperties();
        for (FormProperty formProperty : formProperties1) {
            logger.info("taskFormProperty = {}", ToStringBuilder.reflectionToString(formProperty));
        }

        Map<String, String> formProperties2 = Maps.newHashMap();
        formProperties2.put("yesOrNo", "yes");
        formService.submitTaskFormData(task.getId(), formProperties2);

        Task task1 = activitiRule.getTaskService().createTaskQuery().taskId(task.getId()).singleResult();
        logger.info("任务是否还存在 = {}", task1 != null);

    }
}
