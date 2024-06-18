package org.ko.orm.spring.jdbc;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.ko.orm.jdbc.dao.StudentDAO;
import org.ko.orm.jdbc.domain.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class PowerTest {

    private ApplicationContext ctx = null;

    @Before public void setup () {
        ctx = new ClassPathXmlApplicationContext("spring-jdbc.xml");
        System.out.println("setup");
    }

    @After public void tearDown () {
        ctx = null;
        System.out.println("tearDown");
    }

    @Test public void dataSourceTest () {
        DataSource dataSource = DataSource.class.cast(ctx.getBean("dataSource"));
        Assert.assertNotNull(dataSource);
        System.out.println("dataSourceTest");
    }

    @Test public void jdbcTemplateTest () {
        JdbcTemplate jdbcTemplate = JdbcTemplate.class.cast(ctx.getBean("jdbcTemplate"));
        Assert.assertNotNull(jdbcTemplate);
        System.out.println("jdbcTemplateTest");
    }

    @Test public void studentDAOSpringJdbcQueryTest () {
        StudentDAO studentDAO = StudentDAO.class.cast(ctx.getBean("studentDAO"));
        List<Student> students = studentDAO.query();

        students.forEach(s -> {
            System.out.println(s.getId());
            System.out.println(s.getName());
            System.out.println(s.getAge());
        });
    }

    @Test public void studentDAOSpringJdbcUpdateTest () {
        StudentDAO studentDAO = StudentDAO.class.cast(ctx.getBean("studentDAO"));

        Student student = new Student();
        student.setName("马六");
        student.setAge(24);
        int result = studentDAO.save(student);
        System.out.println(result);
    }


}
