package org.ko.orm.jdbc.dao;

import org.ko.orm.jdbc.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOSpringJdbcImpl implements StudentDAO{

    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> query() {
        List<Student> students = new ArrayList<Student>();
        //定义sql
        String sql = "SELECT * FROM student";
        jdbcTemplate.query(sql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Student student = new Student();
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                students.add(student);
            }
        });
        return students;
    }

    @Override
    public int save(Student student) {
        int result = 0;
        //定义sql
        String sql = "INSERT INTO student(name, age) VALUES (?, ?)";
        return jdbcTemplate.update(sql, student.getName(), student.getAge());
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
