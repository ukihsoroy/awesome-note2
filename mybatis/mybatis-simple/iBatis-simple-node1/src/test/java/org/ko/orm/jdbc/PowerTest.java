package org.ko.orm.jdbc;

import org.junit.Test;
import org.ko.orm.jdbc.dao.StudentDAO;
import org.ko.orm.jdbc.dao.StudentDAOImpl;
import org.ko.orm.jdbc.domain.Student;

import java.util.List;

public class PowerTest {

    /**
     * 查询
     */
    @Test public void queryTest () {
        StudentDAO studentDAO = new StudentDAOImpl();
        List<Student> students = studentDAO.query();

        students.forEach(s -> {
            System.out.println(s.getId());
            System.out.println(s.getName());
            System.out.println(s.getAge());
        });

    }

    /**
     * 插入
     */
    @Test public void insertTest () {
        StudentDAO studentDAO = new StudentDAOImpl();

        Student student = new Student();
        student.setName("马六");
        student.setAge(24);

        int ret = studentDAO.save(student);
        System.out.println(ret);
    }
}
