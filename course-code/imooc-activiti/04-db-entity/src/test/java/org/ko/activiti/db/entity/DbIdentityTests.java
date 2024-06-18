package org.ko.activiti.db.entity;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
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
public class DbIdentityTests {

    private static final Logger logger = LoggerFactory.getLogger(DbIdentityTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-mysql.cfg.xml");

    /**
     * 用户关系
     */
    @Test public void testIdentity() {
        IdentityService identityService = activitiRule.getIdentityService();
        User user1 = identityService.newUser("user1");
        user1.setFirstName("firstName");
        user1.setLastName("lastName");
        user1.setEmail("user1@126.com");
        user1.setPassword("pwd");
        identityService.saveUser(user1);

        User user2 = identityService.newUser("user2");
        identityService.saveUser(user2);

        Group group1 = identityService.newGroup("group1");
        group1.setName("for test");
        identityService.saveGroup(group1);

        identityService.createMembership(user1.getId(), group1.getId());
        identityService.createMembership(user2.getId(), group1.getId());

        identityService.setUserInfo(user1.getId(), "age", "18");
        identityService.setUserInfo(user1.getId(), "address", "beijing");

    }


}
