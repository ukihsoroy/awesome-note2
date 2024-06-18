package org.ko.activiti;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
@RunWith(SpringRunner.class)
public class H2DBTests {

    @Value("${db.url}") String url;

    @Value("${db.username}") String username;

    @Value("${db.password}") String password;

    private Connection conn;

//    @Before
    public void before () {
        try {
            //1- 加载驱动
            Class.forName("org.h2.Driver");
            //2- 获取数据库连接
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void createTables () {
        try {
            //1- 创建Person表, 并插入数据
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE person(id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(200));" +
                    "INSERT INTO person(name) VALUES ('K.O');";
            //2- 执行sql
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
