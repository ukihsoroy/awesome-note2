package org.ko.activiti.core.api;

import com.google.common.collect.Maps;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.*;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * description: TaskServiceTests <br>
 * date: 2020/2/14 14:39 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class TaskServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(TaskServiceTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    @Test public void testTaskService() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my test message!");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);

        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        logger.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.DEFAULT_STYLE));
        logger.info("task.description = {}", task.getDescription());

        taskService.setVariable(task.getId(), "key1", "value1");
        taskService.setVariableLocal(task.getId(), "localKey1", "localValue1");

        Map<String, Object> taskServiceVariables = taskService.getVariables(task.getId());
        Map<String, Object> taskServiceVariablesLocal = taskService.getVariablesLocal(task.getId());

        Map<String, Object> processVariables = activitiRule.getRuntimeService().getVariables(task.getExecutionId());

        logger.info("taskServiceVariables = {}", taskServiceVariables);
        logger.info("taskServiceVariablesLocal = {}", taskServiceVariablesLocal);
        logger.info("processVariables = {}", processVariables);

        //完成程序
        Map<String, Object> completeVar = Maps.newConcurrentMap();
        completeVar.put("cKey1", "cValue1");
        taskService.complete(task.getId(), completeVar);

        //完成后任务自动结束
        Task task1 = taskService.createTaskQuery().taskId(task.getId()).singleResult();
        logger.info("result task = {}", task1);
    }

    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    @Test public void testTaskServiceUser() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my test message!");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);

        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        logger.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.DEFAULT_STYLE));
        logger.info("task.description = {}", task.getDescription());

        //流程发起人
        taskService.setOwner(task.getId(), "user1");

        //不建议这么做，会发生权限问题
//        taskService.setAssignee(task.getId(), "K.O");

        //查询指定了候选人，还没被处理的任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateUser("K.O").taskUnassigned().list();
        for (Task task1 : tasks) {
            try {
                taskService.claim(task1.getId(), "K.O");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        //task构建的关系
        List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(task.getId());
        for (IdentityLink identityLink : identityLinksForTask) {
            logger.info("identityLink = {}", identityLink);
        }

        //查看是我是处理人的数据
        List<Task> list = taskService.createTaskQuery().taskAssignee("K.O").list();

        //完成任务
        for (Task task1 : list) {
            Map<String, Object> variables1 = Maps.newHashMap();
            variables1.put("cKey1", "cValue1");
            taskService.complete(task1.getId(), variables1);
        }

        //查看是否还有任务存在
        List<Task> list1 = taskService.createTaskQuery().taskAssignee("K.O").list();
        logger.info("任务是否还存在, {}", !CollectionUtils.isEmpty(list1));
    }

    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    @Test public void testTaskAttachment() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my test message!");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);

        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        logger.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.DEFAULT_STYLE));
        logger.info("task.description = {}", task.getDescription());

        taskService.createAttachment(
                "url",
                task.getId(),
                task.getProcessInstanceId(),
                "name",
                "desc",
                "/url/test.png");

        List<Attachment> taskAttachments = taskService.getTaskAttachments(task.getId());
        for (Attachment taskAttachment : taskAttachments) {
            logger.info("taskAttachment = {}", ToStringBuilder.reflectionToString(taskAttachment));
        }
    }

    @Deployment(resources = {"my-process-task.bpmn20.xml"})
    @Test public void testTaskComment() {
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("message", "my test message!");
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process", variables);

        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
        logger.info("task = {}", ToStringBuilder.reflectionToString(task, ToStringStyle.DEFAULT_STYLE));
        logger.info("task.description = {}", task.getDescription());

        //event 会自动记录活动
        taskService.setOwner(task.getId(), "user1");
        taskService.setAssignee(task.getId(), "K.O");

        taskService.addComment(task.getId(), task.getProcessInstanceId(), "record note 1.");
        taskService.addComment(task.getId(), task.getProcessInstanceId(), "record note 2.");

        List<Comment> taskComments = taskService.getTaskComments(task.getId());
        for (Comment taskComment : taskComments) {
            logger.info("taskComment = {}", ToStringBuilder.reflectionToString(taskComment));
        }

        //事件记录，记录了task记录的变化
        List<Event> taskEvents = taskService.getTaskEvents(task.getId());
        for (Event taskEvent : taskEvents) {
            logger.info("taskEvent = {}", ToStringBuilder.reflectionToString(taskEvent));
        }
    }

}
