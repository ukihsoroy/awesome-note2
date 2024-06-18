package org.ko.activiti;

import com.google.common.collect.Maps;
import org.activiti.engine.*;
import org.activiti.engine.form.FormProperty;
import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DemoMain {

    private static final Logger logger = LoggerFactory.getLogger(DemoMain.class);

    public static void main(String[] args) throws ParseException {
        logger.info("启动程序");

        // 创建流程引擎
        ProcessEngine processEngine = getProcessEngine();

        // 部署流程定义文件
        ProcessDefinition processDefinition = getProcessDefinition(processEngine);

        // 启动运行流程
        ProcessInstance processInstance = getProcessInstance(processEngine, processDefinition);

        // 处理流程任务
        processTask(processEngine, processInstance);

        logger.info("结束程序");
    }

    private static void processTask(ProcessEngine processEngine, ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {

            TaskService taskService = processEngine.getTaskService();
            List<Task> tasks = taskService.createTaskQuery().list();
            for (Task task : tasks) {
                logger.info("待处理任务 [{}]", task.getName());
                FormService formService = processEngine.getFormService();
                TaskFormData taskFormData = formService.getTaskFormData(task.getId());
                List<FormProperty> formProperties = taskFormData.getFormProperties();
                Map<String, Object> variables = getMap(scanner, formProperties);
                //提交任务
                taskService.complete(task.getId(), variables);
                //刷新流程实例
                processInstance = processEngine.getRuntimeService()
                        .createProcessInstanceQuery()
                        .processInstanceId(processInstance.getId())
                        .singleResult();
            }
            logger.info("待处理任务数量 [{}]", tasks.size());
        }
        scanner.close();
    }

    private static Map<String, Object> getMap(Scanner scanner, List<FormProperty> formProperties) throws ParseException {
        Map<String, Object> variables = Maps.newHashMap();
        for (FormProperty formProperty : formProperties) {
            String line = null;
            if (formProperty.getType() instanceof StringFormType) {
                logger.info("请输入 {} ?", formProperty.getName());
                line = scanner.nextLine();
                variables.put(formProperty.getId(), line);
            } else if (formProperty.getType() instanceof DateFormType) {
                logger.info("请输入 {} ? 格式 (yyyy-MM-dd)", formProperty.getName());
                line = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(line);
                variables.put(formProperty.getId(), date);
            } else {
                logger.info("类型暂不支持 {}", formProperty.getType());
            }
            logger.info("您输入的内容是 {}", line);
        }
        return variables;
    }

    private static ProcessInstance getProcessInstance(ProcessEngine processEngine, ProcessDefinition processDefinition) {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
        logger.info("启动流程 [{}]", processInstance.getProcessDefinitionKey());
        return processInstance;
    }

    private static ProcessDefinition getProcessDefinition(ProcessEngine processEngine) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.addClasspathResource("second_approve.bpmn20.xml");
        Deployment deployment = deploymentBuilder.deploy();
        String deploymentId = deployment.getId();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        logger.info("流程定义文件 [{}] 流程ID [{}]", processDefinition.getName(), processDefinition.getId());
        return processDefinition;
    }

    private static ProcessEngine getProcessEngine() {
        //创建了一个单点的基于内存的流程引擎
        ProcessEngineConfiguration cfg = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
        ProcessEngine processEngine = cfg.buildProcessEngine();
        String name = processEngine.getName();
        String version = ProcessEngine.VERSION;

        logger.info("流程引擎名称[{}]， 版本[{}]", name, version);
        return processEngine;
    }
}
