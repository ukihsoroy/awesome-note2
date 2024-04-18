package org.ko.activiti.db.entity;

import com.google.common.collect.Maps;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
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

/**
 * description: 通用数据库测试 <br>
 * date: 2020/2/16 19:34 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class DbHistoryTests {

    private static final Logger logger = LoggerFactory.getLogger(DbHistoryTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    /**
     * 用户关系
     */
    @Test public void testHistory() {
        //部署流程定义文件
        activitiRule.getRepositoryService()
                .createDeployment()
                .name("测试部署")
                .addClasspathResource("my-process.bpmn20.xml")
                .deploy();

        //启动流程
        RuntimeService runtimeService = activitiRule.getRuntimeService();

        //传递变量
        Map<String, Object> variables = Maps.newHashMap();
        variables.put("key1", "value1");
        variables.put("key2", "value2");
        variables.put("key3", "value3");

        //启动流程并且传递变量
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("my-process", variables);

        //运行时修改变量的值
        runtimeService.setVariable(processInstance.getId(), "key1", "value1_1");

        //获取唯一运行的task
        TaskService taskService = activitiRule.getTaskService();
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();

        //为task设置拥有者
        taskService.setOwner(task.getId(), "user1");

        //上传附件
        taskService.createAttachment(
                "url",
                task.getId(),
                processInstance.getId(),
                "测试网址",
                "测试网址描述",
                "https://www.baidu.com");

        //添加评论
        taskService.addComment(task.getId(), processInstance.getId(), "record note1");
        taskService.addComment(task.getId(), processInstance.getId(), "record note2");

        //使用form的方式提交task任务
        Map<String, String> properties = Maps.newHashMap();
        properties.put("key2", "value2_1");
        properties.put("key4", "value4_1");
        activitiRule.getFormService().submitTaskFormData(task.getId(), properties);


    }


}
