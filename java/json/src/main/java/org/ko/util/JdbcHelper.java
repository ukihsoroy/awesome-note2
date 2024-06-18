package org.ko.util;

import java.sql.*;

public final class JdbcHelper {

    private static final String url = "jdbc:mysql:///art-prototype?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private static final String user = "";
    private static final String password = "";

    private static JdbcHelper instance = null;

    public static JdbcHelper getInstance() {
        if (instance == null) {
            synchronized (JdbcHelper.class) {
                if (instance == null) {
                    instance = new JdbcHelper();
                }
            }
        }
        return instance;
    }

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }


    public void free (ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null)
                rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private JdbcHelper() {}
}
