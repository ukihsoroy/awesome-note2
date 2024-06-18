package org.ko.ibatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ko.ibatis.dao.EmployeeMapper;
import org.ko.ibatis.domain.Employee;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ConfigTest {

    String path = "ibatis/mybatis-config.xml";

    /**
     * SqlSession 是线程不安全的-不该出现在final区和全局变量
     * 这里是测试 无关紧要
     * 但通常不建议这么写
     */
    SqlSession session = null;

    @Before
    public void config () throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(path);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        session = sqlSessionFactory.openSession();
    }

    @After
    public void destory () {
        session = null;
    }


    @Test
    public void configTest () throws IOException {
        InputStream inputStream = Resources.getResourceAsStream(path);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        Assert.assertNotNull(mapper);
    }

    @Test
    public void selectTest () {
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Employee> employees = mapper.findAll();
        for (Employee employee : employees) {
            System.out.println(employee.toString());
        }
    }
}
