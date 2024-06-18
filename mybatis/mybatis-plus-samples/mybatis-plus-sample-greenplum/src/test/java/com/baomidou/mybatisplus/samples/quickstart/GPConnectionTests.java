package com.baomidou.mybatisplus.samples.quickstart;

import java.sql.*;

/**
 * description: GPConnectionTests <br>
 * date: 4/13/2020 16:37 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class GPConnectionTests {

    public static void main(String[] args) throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            Connection db = DriverManager.getConnection("jdbc:postgresql://gpdb.rds.aliyuncs.com:3432/greenplum","","");
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from t_user;");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print("    |    ");
                System.out.print(rs.getString(2));
                System.out.print("    |    ");
                System.out.print(rs.getString(3));
                System.out.print("    |    ");
                System.out.print(rs.getString(4));
                System.out.print("    |    ");
                System.out.print(rs.getString(5));
                System.out.print("    |    ");
                System.out.print(rs.getString(6));
                System.out.print("    |    ");
                System.out.print(rs.getString(7));
                System.out.print("    |    ");
                System.out.print(rs.getString(8));
                System.out.print("    |    ");
                System.out.print(rs.getString(9));
                System.out.print("    |    ");
                System.out.print(rs.getString(10));
                System.out.print("    |    ");
                System.out.println(rs.getString(11));
            }
            rs.close();
            st.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
