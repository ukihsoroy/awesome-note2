package org.ko.activiti.config;

import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试
 */
public class ConfigTests {

    private static final Logger logger = LoggerFactory.getLogger(ConfigTests.class);

    @Test
    public void testConfig1() {
        //基于默认的配置文件，初始化流程引擎，resource目录下需要有对应的activiti.cfg.xml配置文件，要不报错
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResourceDefault();

        logger.info("configuration {}", configuration);
    }

    @Test
    public void testConfig2() {
        //纯粹自己创建的对象
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration();

        logger.info("configuration {}", configuration);
    }

}
