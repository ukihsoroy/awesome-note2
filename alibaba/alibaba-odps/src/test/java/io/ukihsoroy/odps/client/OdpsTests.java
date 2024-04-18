package io.ukihsoroy.odps.client;


import org.junit.jupiter.api.Test;

import java.sql.*;


public class OdpsTests {

    private static final String DRIVER_NAME = "com.aliyun.odps.jdbc.OdpsDriver";

    @Test
    public void test1() throws SQLException {

        try {
            Class.forName(DRIVER_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Connection conn = DriverManager.getConnection(
                "jdbc:odps:https://service.odps.aliyun.com/api?project=",
                "", "");

        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO t_user VALUES(?, ?, ?, ?)");

        pstmt.setString(1, "1");
        pstmt.setString(2, "K.O1");
        pstmt.setLong(3, 18L);
        pstmt.setDate(4, new Date(1L));
        pstmt.addBatch();

        pstmt.setString(1, "2");
        pstmt.setString(2, "K.O2");
        pstmt.setLong(3, 18L);
        pstmt.setDate(4, new Date(1L));
        pstmt.addBatch();

        int[] ret = pstmt.executeBatch();

        assert ret[0] == 1;
        assert ret[1] == 1;

        pstmt.close();
        conn.close();
    }
}
