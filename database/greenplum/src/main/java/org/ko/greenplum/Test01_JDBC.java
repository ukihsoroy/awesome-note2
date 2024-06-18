package org.ko.greenplum;

import org.ko.greenplum.helper.GreenplumHelper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试
 */
public class Test01_JDBC {
    public static void main(String[] args) {
        try {
            Connection db = GreenplumHelper.getInstance().getConnection();
            Statement st = db.createStatement();
            ResultSet rs = st.executeQuery("select * from sales;");
            while (rs.next()) {
                System.out.print(rs.getString(1));
                System.out.print("    |    ");
                System.out.print(rs.getString(2));
                System.out.print("    |    ");
                System.out.print(rs.getString(3));
                System.out.print("    |    ");
                System.out.print(rs.getString(4));
                System.out.print("    |    ");
            }
            GreenplumHelper.free(rs, st, db);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
