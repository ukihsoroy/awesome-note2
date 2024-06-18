package io.ukihsoroy.automation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@SpringBootTest
@RunWith(SpringRunner.class)
public class OdpsTests {

    @Autowired
    private JdbcTemplate odpsTemplate;

    @Test
    public void test1() throws SQLException {

        Connection conn = odpsTemplate.getDataSource().getConnection();

        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO t_user VALUES(?, ?, ?, ?)");

        pstmt.setInt(1, 1);
        pstmt.setString(2, "K.O1");
        pstmt.setInt(2, 18);
        pstmt.setDate(2, new Date(1L));
        pstmt.addBatch();

        pstmt.setInt(1, 1);
        pstmt.setString(2, "K.O2");
        pstmt.setInt(2, 18);
        pstmt.setDate(2, new Date(1L));
        pstmt.addBatch();

        int[] ret = pstmt.executeBatch();

        assert ret[0] == 1;
        assert ret[1] == 1;

        pstmt.close();
        conn.close();
    }
}
