package org.ko.greenplum.helper;

import java.sql.*;

public final class GreenplumHelper {

    private static final String url = "jdbc:postgresql://gp-2ze06nb5tczrs8r27o.gpdb.rds.aliyuncs.com:3432/greenplum";
    private static final String user = "greenplum";
    private static final String password = "Miao2014";

    private static GreenplumHelper instance = null;

    public static GreenplumHelper getInstance() {
        if (instance == null) {
            synchronized (GreenplumHelper.class) {
                if (instance == null) {
                    instance = new GreenplumHelper();
                }
            }
        }
        return instance;
    }

    static {
        try {
            Class.forName("org.postgresql.Driver");
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


    public static void free (ResultSet rs, Statement st, Connection conn) {
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

    private GreenplumHelper() {}
}
