package org.ko.orm.jdbc.dao;

import org.ko.orm.jdbc.domain.Student;

import java.util.List;

/**
 * StudentDAO访问接口
 */
public interface StudentDAO {

    /**
     * 查询所有学生
     * @return 所有学生
     */
    List<Student> query ();

    int save (Student student);
}
