package org.ko.activiti.core.api;

import org.activiti.engine.ManagementService;
import org.activiti.engine.impl.cmd.AbstractCustomSqlExecution;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.management.TablePage;
import org.activiti.engine.management.TablePageQuery;
import org.activiti.engine.runtime.DeadLetterJobQuery;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.JobQuery;
import org.activiti.engine.runtime.SuspendedJobQuery;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.ko.activiti.mapper.MyCustomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * description: ManagerServiceTests <br>
 * date: 2020/2/15 22:43 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class ManagerServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(ManagerServiceTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_job.cfg.xml");

    /**
     * 异步任务
     */
    @Deployment(resources = {"my-process-job.bpmn20.xml"})
    @Test public void testJobQuery() {
        ManagementService managementService = activitiRule.getManagementService();

        List<Job> jobs = managementService.createTimerJobQuery().list();
        for (Job job : jobs) {
            logger.info("job = {}", job);
        }
        logger.info("jobs size = {}", jobs.size());

        JobQuery jobQuery = managementService.createJobQuery();
        SuspendedJobQuery suspendedJobQuery = managementService.createSuspendedJobQuery();
        //需要运维同学关注一下，是什么原因没执行下去
        DeadLetterJobQuery deadLetterJobQuery = managementService.createDeadLetterJobQuery();
    }

    @Deployment(resources = {"my-process-job.bpmn20.xml"})
    @Test public void testTablePageQuery() {
        ManagementService managementService = activitiRule.getManagementService();

        TablePage tablePage = managementService.createTablePageQuery()
                .tableName(managementService.getTableName(ProcessDefinitionEntity.class))
                .listPage(0, 100);

        List<Map<String, Object>> rows = tablePage.getRows();
        for (Map<String, Object> row : rows) {
            logger.info("row = {}", row);
        }
    }

    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testCustomSql() {
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        ManagementService managementService = activitiRule.getManagementService();
        List<Map<String, Object>> maps = managementService.executeCustomSql(new AbstractCustomSqlExecution<MyCustomMapper, List<Map<String, Object>>>(MyCustomMapper.class) {
            @Override
            public List<Map<String, Object>> execute(MyCustomMapper customMapper) {
                return customMapper.findAll();
            }
        });

        for (Map<String, Object> map : maps) {
            logger.info("map = {}", map);
        }
    }

    @Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testCommand() {
        activitiRule.getRuntimeService().startProcessInstanceByKey("my-process");
        ManagementService managementService = activitiRule.getManagementService();

        ProcessDefinitionEntity processDefinitionEntity = managementService.executeCommand(new Command<ProcessDefinitionEntity>() {
            @Override
            public ProcessDefinitionEntity execute(CommandContext commandContext) {
                ProcessDefinitionEntity latestProcessDefinitionByKey = commandContext.getProcessDefinitionEntityManager().findLatestProcessDefinitionByKey("my-process");
                return latestProcessDefinitionByKey;
            }
        });

        logger.info("processDefinitionEntity = {}", processDefinitionEntity);
    }

}
