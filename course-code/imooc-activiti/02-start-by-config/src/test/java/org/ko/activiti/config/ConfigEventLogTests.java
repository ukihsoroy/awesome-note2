package org.ko.activiti.config;

import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * MDC测试
 */
public class ConfigEventLogTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    /**
     * 1. 不写参数，使用默认的
     * 2. activiti_mdc.cfg.xml，添加了mdc的拦截器，打印树
     * 3. 基于mdc配置文件，添加了event log配置
     */
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_eventlog.cfg.xml");

    @Test
    //辅助单元测试的注解
    @Deployment(resources = {"org.ko.activiti/my-process_event.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());

        List<EventLogEntry> eventLogEntries = activitiRule.getManagementService()
                .getEventLogEntriesByProcessInstanceId(processInstance.getProcessInstanceId());

        for (EventLogEntry eventLogEntry : eventLogEntries) {
            logger.info("eventLog.type = {}, eventLog.data = {}", eventLogEntry.getType(), new String(eventLogEntry.getData()));
        }

        logger.info("eventLogEntries.size = {}", eventLogEntries.size());
    }

}
