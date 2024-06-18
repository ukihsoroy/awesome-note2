package org.ko.orm.jdbc.dao;


import org.ko.orm.jdbc.domain.Student;
import org.ko.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * StudentDAO访问接口实现类，通过最原始的jdbc的方式操作
 */
public class StudentDAOImpl implements StudentDAO {

    public List<Student> query() {
        List<Student> students = new ArrayList<Student>();
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        //定义sql
        String sql = "SELECT * FROM student";
        try {
            //创建查询对象
            preparedStatement = connection.prepareStatement(sql);
            //执行查询获取结果
            resultSet = preparedStatement.executeQuery();
            //解析结果
            Student student = null;
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                student = new Student();
                student.setId(id);
                student.setName(name);
                student.setAge(age);
                students.add(student);
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        } finally {
            JDBCUtil.release(resultSet, preparedStatement, connection);
        }
        return students;
    }

    @Override
    public int save(Student student) {
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = null;
        int result = 0;
        //定义sql
        String sql = "INSERT INTO student(name, age) VALUES (?, ?)";
        try {
            //创建查询对象
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            //执行查询获取结果
            result = preparedStatement.executeUpdate();
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        } finally {
            JDBCUtil.release(null, preparedStatement, connection);
        }
        return result;
    }

}
