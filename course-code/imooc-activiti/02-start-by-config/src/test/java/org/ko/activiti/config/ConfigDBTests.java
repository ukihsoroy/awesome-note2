package org.ko.activiti.config;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据库配置
 */
public class ConfigDBTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    @Test
    public void testConfig1() {
        //基于默认的配置文件，初始化流程引擎，resource目录下需要有对应的activiti.cfg.xml配置文件，要不报错
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();

        logger.info("configuration {}", configuration);

        ProcessEngine processEngine = configuration.buildProcessEngine();
        logger.info("获取流程引擎 {}", processEngine);

        processEngine.close();

        logger.info("over");

    }

    @Test
    public void testConfig2() {
        //基于默认的配置文件，初始化流程引擎，resource目录下需要有对应的activiti.cfg.xml配置文件，要不报错
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti_druid.cfg.xml");

        logger.info("configuration {}", configuration);

        ProcessEngine processEngine = configuration.buildProcessEngine();
        logger.info("获取流程引擎 {}", processEngine);

        processEngine.close();

        logger.info("over");

    }

}
