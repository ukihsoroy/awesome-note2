package org.ko.activiti.config;

import org.activiti.engine.runtime.Job;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * job配置测试
 */
public class ConfigJobTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    /**
     * 1. 不写参数，使用默认的
     * 2. activiti_job.cfg.xml
     */
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti_job.cfg.xml");

    @Test
    //辅助单元测试的注解
    @Deployment(resources = {"org.ko.activiti/my-process_job.bpmn20.xml"})
    public void test() {
        logger.info("start");

        List<Job> jobs = activitiRule.getManagementService().createTimerJobQuery().list();

        for (Job job : jobs) {
            logger.info("job: {}, retry: {}", job, job.getRetries());
        }

        logger.info("job.size: {}", jobs.size());

        //阻塞主线程
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("end");
    }

}
