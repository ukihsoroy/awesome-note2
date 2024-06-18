package org.ko.activiti.db.entity;

import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntity;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntityImpl;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: 通用数据库测试 <br>
 * date: 2020/2/16 19:34 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class DbRepositoryTests {

    private static final Logger logger = LoggerFactory.getLogger(DbRepositoryTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    /**
     * 部署流程会自动在资源库添加数据
     */
    @Test public void testDeploy() {
        activitiRule.getRepositoryService()
                .createDeployment()
                .name("二次审批流程")
                .addClasspathResource("second_approve.bpmn20.xml")
                .deploy();
    }

    /**
     * 挂起流程定义文件
     * second_approve:2:7504
     */
    @Test public void testSuspend() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        //挂起
        repositoryService.suspendProcessDefinitionById("second_approve:2:7504");

        boolean suspended = repositoryService.isProcessDefinitionSuspended("second_approve:2:7504");
        logger.info("suspended = {}", suspended);
    }




}
