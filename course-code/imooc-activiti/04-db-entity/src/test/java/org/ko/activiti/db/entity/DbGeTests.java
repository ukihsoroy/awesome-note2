package org.ko.activiti.db.entity;

import org.activiti.engine.ManagementService;
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
public class DbGeTests {

    private static final Logger logger = LoggerFactory.getLogger(DbGeTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    /**
     * 部署流程会自动在资源库添加数据
     */
    @Test public void testByteArray() {
        activitiRule.getRepositoryService()
                .createDeployment()
                .name("测试部署")
                .addClasspathResource("my-process.bpmn20.xml")
                .deploy();
    }

    /**
     * 手动写一条数据到通用数据库
     */
    @Test public void testInsertByteArray() {
        ManagementService managementService = activitiRule.getManagementService();

        managementService.executeCommand(new Command<Object>() {
            @Override
            public Object execute(CommandContext commandContext) {
                ByteArrayEntity entity = new ByteArrayEntityImpl();
                entity.setName("test");
                entity.setBytes("test message!".getBytes());
                commandContext.getByteArrayEntityManager().insert(entity);
                return null;
            }
        });
    }


}
