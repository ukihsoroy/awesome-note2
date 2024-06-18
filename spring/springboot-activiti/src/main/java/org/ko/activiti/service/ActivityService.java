package org.ko.activiti.service;

import org.activiti.bpmn.model.FlowNode;
import org.activiti.engine.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.CancelEndEventActivityBehavior;
import org.activiti.engine.impl.bpmn.behavior.FlowNodeActivityBehavior;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.impl.persistence.entity.IdentityLinkEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Throwable.class)
public class ActivityService {

    @Autowired private RuntimeService runtimeService;

    @Autowired private TaskService taskService;

    @Autowired private HistoryService historyService;

    @Autowired private RepositoryService repositoryService;

    @Autowired private ProcessEngine processEngine;

    /**
     * 开始流程图
     * @param personId
     * @param companyId
     */
    public void startProcess (Long personId, Long companyId) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("personId", personId);
        variables.put("companyId", companyId);
        runtimeService.startProcessInstanceByKey("流程图ID", variables);
    }


    /**
     * 获取某个人的任务列表
     * @param assignee
     * @return
     */
    public List<Task> getTasks (String assignee) {
        return taskService.createTaskQuery().taskCandidateUser(assignee).list();
    }

    /**
     * 完成任务
     * @param joinApproved
     * @param taskId
     */
    public void completeTask (Boolean joinApproved, String taskId) {
        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("joinApproved", joinApproved);
        taskService.complete(taskId, taskVariables);
    }


    /**
     *
     * <p>流程跳转</p>
     *
     * @param processInstanceId 值为 processInstanceId
     * @param destinationTaskId 值为 xml中 userTask的id
     * @param rejectMessage
     */
    public void rejectTask(String processInstanceId,
                           String destinationTaskId,
                           String rejectMessage,
                           Map<String, Object> variables) {
        // 获得当前任务的对应实列
        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        // 当前任务key
        String currentTaskId = task.getTaskDefinitionKey();

        //获得当前流程的定义模型
        ProcessDefinitionEntity processDefinition = ProcessDefinitionEntity.class
                .cast(RepositoryServiceImpl.class
                        .cast(repositoryService)
                        .getDeployedProcessDefinition(task.getProcessDefinitionId()));

        // 获得当前流程定义模型的所有任务节点
        List<ActivityImpl> activities = processDefinition.getActivities();

        //当前活动节点
        Optional<ActivityImpl> currentOptional = activities.stream()
                .filter(activity -> Objects.equals(currentTaskId, activity.getId())).findFirst();
        // 驳回目标节点
        Optional<ActivityImpl> destinationOptional = activities.stream()
                .filter(activity -> Objects.equals(destinationTaskId, activity.getId())).findFirst();

         //保存当前活动节点的流程想参数
        List<PvmTransition> transitions = new ArrayList<>(0);

        if (currentOptional.isPresent() && destinationOptional.isPresent()) {
            ActivityImpl currentActivity = currentOptional.get();
            ActivityImpl destinationActivity = destinationOptional.get();
            transitions.addAll(currentActivity.getOutgoingTransitions());

            // 清空当前活动几点的所有流出项
            currentActivity.getOutgoingTransitions().clear();

            // 为当前节点动态创建新的流出项
            TransitionImpl newTransition = currentActivity.createOutgoingTransition();

            // 为当前活动节点新的流出目标指定流程目标
            newTransition.setDestination(destinationActivity);

            // 保存驳回意见
            task.setDescription(rejectMessage);// 设置驳回意见
            taskService.saveTask(task);

            // 添加批注
            Authentication.setAuthenticatedUserId("userId");
            taskService.addComment(task.getId(), processInstanceId, rejectMessage);
            // 执行当前任务驳回到目标任务draft
            taskService.complete(task.getId(), variables);

            // 清除目标节点的新流入项
            destinationActivity.getIncomingTransitions().remove(newTransition);

            // 清除原活动节点的临时流程项
            currentActivity.getOutgoingTransitions().clear();

            // 还原原活动节点流出项参数
            currentActivity.getOutgoingTransitions().addAll(transitions);
        }


    }

}
