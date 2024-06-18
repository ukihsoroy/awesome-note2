package org.ko.activiti.core.api;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.test.ActivitiRule;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * description: 身份管理服务 <br>
 * date: 2020/2/15 15:14 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class IdentityServiceTests {

    private static final Logger logger = LoggerFactory.getLogger(IdentityServiceTests.class);

    @Rule
    public ActivitiRule activitiRule = new ActivitiRule();

    @Test public void testIdentityService() {
        IdentityService identityService = activitiRule.getIdentityService();

        //创建用户
        User user1 = identityService.newUser("user1");
        user1.setEmail("ko.shen@hotmail.com");
        User user2 = identityService.newUser("user2");
        user2.setEmail("814777765@qq.com");
        identityService.saveUser(user1);
        identityService.saveUser(user2);

        //创建组
        Group group1 = identityService.newGroup("group1");
        identityService.saveGroup(group1);
        Group group2 = identityService.newGroup("group2");
        identityService.saveGroup(group2);

        //创建用户与组的关系
        identityService.createMembership("user1", "group1");
        identityService.createMembership("user2", "group1");
        identityService.createMembership("user1", "group2");

        //修改用户
        User user11 = identityService.createUserQuery().userId("user1").singleResult();
        user11.setFirstName("K.O");
        identityService.saveUser(user11);

        //查询用户
        List<User> users = identityService.createUserQuery().memberOfGroup("group1").list();
        for (User user : users) {
            logger.info("user = {}", ToStringBuilder.reflectionToString(user));
        }

        //查询组
        List<Group> groups = identityService.createGroupQuery().groupMember("user1").list();
        for (Group group : groups) {
            logger.info("group = {}", ToStringBuilder.reflectionToString(group));
        }

    }

}
