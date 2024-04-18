package org.ko.activiti.db.entity;

import com.google.common.collect.Lists;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

/**
 * description: 数据库配置相关内容 <br>
 * date: 2020/2/16 19:20 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class DbConfigTests {

    private static final Logger logger = LoggerFactory.getLogger(DbConfigTests.class);

    /**
     * 查看表个数
     */
    @Test public void testDefaultDbConfig () {
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault()
                .buildProcessEngine();

        ManagementService managementService = processEngine.getManagementService();
        Map<String, Long> tableCount = managementService.getTableCount();
        ArrayList<String> tables = Lists.newArrayList(tableCount.keySet());
        Collections.sort(tables);

        for (String table : tables) {
            logger.info("table name = {}", table);
        }

        logger.info("table size = {}", tables.size());
    }

    /**
     * 排除history和identity
     */
    @Test public void testDbConfigWithoutHistoryAndIdentity() {
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-mysql.cfg.xml")
                .buildProcessEngine();

        ManagementService managementService = processEngine.getManagementService();
        Map<String, Long> tableCount = managementService.getTableCount();
        ArrayList<String> tables = Lists.newArrayList(tableCount.keySet());
        Collections.sort(tables);

        for (String table : tables) {
            logger.info("table name = {}", table);
        }

        logger.info("table size = {}", tables.size());
    }

    /**
     * 测试删除表
     */
    @Test public void testDropTable() {
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti-mysql.cfg.xml")
                .buildProcessEngine();

        ManagementService managementService = processEngine.getManagementService();
        managementService.executeCommand(new Command<Object>() {
            @Override
            public Object execute(CommandContext commandContext) {
                commandContext.getDbSqlSession().dbSchemaDrop();
                logger.info("删除表结构。");
                return null;
            }
        });
    }

}
