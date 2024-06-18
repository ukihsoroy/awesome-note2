package org.ko.activiti.core.api;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * description: 流程管理服务 <br>
 * date: 2020/2/13 16:27 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class RepositoryServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(RepositoryServiceTests.class);

    //空的，使用默认的流程配置文件
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    /**
     * 单词部署
     */
    @Test
    public void testRepositoryService1() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        //将配置文件，配置到数据库
        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        deploymentBuilder.name("测试部署资源")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");

        //部署流程文件到数据库
        Deployment deploy = deploymentBuilder.deploy();
        logger.info("deploy = {}", deploy);

        //通过部署ID去查询，演示repository
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        Deployment deployment = deploymentQuery.deploymentId(deploy.getId()).singleResult();

        //查看流程定义文件
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.deploymentId(deploy.getId()).list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            logger.info("processDefinition = {}, id = {}, name = {}, version = {}", processDefinition, processDefinition.getId(), processDefinition.getName(), processDefinition.getVersion());
        }

        logger.info("processDefinitions.size = {}", processDefinitions.size());
    }

    /**
     * 部署多次但流程文件相同
     */
    @Test
    public void testRepositoryService2() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        //部署第一个
        DeploymentBuilder deploymentBuilder1 = repositoryService.createDeployment();
        deploymentBuilder1.name("测试部署资源1")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");
        Deployment deploy1 = deploymentBuilder1.deploy();
        logger.info("deploy1 = {}", deploy1);

        //部署第二个
        DeploymentBuilder deploymentBuilder2 = repositoryService.createDeployment();
        deploymentBuilder2.name("测试部署资源2")
                .addClasspathResource("my-process.bpmn20.xml")
                .addClasspathResource("second_approve.bpmn20.xml");
        Deployment deploy2 = deploymentBuilder2.deploy();
        logger.info("deploy2 = {}", deploy2);

        //通过部署ID去查询，演示repository
        DeploymentQuery deploymentQuery = repositoryService.createDeploymentQuery();
        List<Deployment> deployments = deploymentQuery.orderByDeploymenTime().asc().list();
        for (Deployment deployment : deployments) {
            logger.info("deployment = {}, id = {}, key = {}, name = {}, time = {}",
                    deployment,
                    deployment.getId(),
                    deployment.getKey(),
                    deployment.getName(),
                    deployment.getDeploymentTime());
        }
        logger.info("deployments.size = {}", deployments.size());

        //查看流程定义文件
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> processDefinitions = processDefinitionQuery.orderByProcessDefinitionKey().asc().list();
        for (ProcessDefinition processDefinition : processDefinitions) {
            logger.info("processDefinition = {}, id = {}, name = {}, version = {}", processDefinition, processDefinition.getId(), processDefinition.getName(), processDefinition.getVersion());
        }

        logger.info("processDefinitions.size = {}", processDefinitions.size());
    }

    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testSuspend() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        logger.info("processDefinition.id = {}", processDefinition.getId());

        //暂停流程
        repositoryService.suspendProcessDefinitionById(processDefinition.getId());

        try {
            logger.info("开始启动");
            //暂停状态的流程不能开始，会抛异常
            activitiRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
            logger.info("启动成功");
        } catch (Exception e) {
            logger.info("启动失败， {}", e.getMessage());
        }

        repositoryService.activateProcessDefinitionById(processDefinition.getId());
        logger.info("开始启动");
        //抛出异常
        activitiRule.getRuntimeService().startProcessInstanceById(processDefinition.getId());
        logger.info("启动成功");
    }

    @org.activiti.engine.test.Deployment(resources = {"my-process.bpmn20.xml"})
    @Test public void testCandidateStarter() {
        RepositoryService repositoryService = activitiRule.getRepositoryService();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        logger.info("processDefinition.id = {}", processDefinition.getId());

        repositoryService.addCandidateStarterUser(processDefinition.getId(), "user");
        repositoryService.addCandidateStarterGroup(processDefinition.getId(), "group_manager");

        List<IdentityLink> identityLinks = repositoryService.getIdentityLinksForProcessDefinition(processDefinition.getId());

        for (IdentityLink identityLink : identityLinks) {
            logger.info("identityLink = {}", identityLink);
        }

        repositoryService.deleteCandidateStarterGroup(processDefinition.getId(), "group_manager");
        repositoryService.deleteCandidateStarterUser(processDefinition.getId(), "user");
    }

}
