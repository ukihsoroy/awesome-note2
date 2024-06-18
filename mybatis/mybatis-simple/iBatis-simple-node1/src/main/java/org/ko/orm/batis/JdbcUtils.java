package org.ko.orm.batis;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 

  /**
   * @author K.O
   *
   */
public final class JdbcUtils { 
    private String url = "jdbc:mysql://127.0.0.1:3306/ko?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    private String username = "ko";
    private String password = "tiger";

    private static JdbcUtils instance = null; 

    private JdbcUtils() { 
    } 

    public static JdbcUtils getInstance() { 
        if (instance == null) { 
            synchronized (JdbcUtils.class) { 
                if (instance == null) { 
                    instance = new JdbcUtils(); 
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

    public Connection getConnection() throws SQLException { 
        return DriverManager.getConnection(url, username, password);
    } 

    public static void free (ResultSet rs, Statement st, Connection conn) {
        try { 
            if (rs != null) 
                rs.close(); 
        } catch (SQLException e) { 
            e.printStackTrace(); 
        } finally { 
            try { 
                if (st != null) 
                    st.close(); 
            } catch (SQLException e) { 
                e.printStackTrace(); 
            } finally { 
                if (conn != null) 
                    try { 
                        conn.close(); 
                    } catch (SQLException e) { 
                        e.printStackTrace(); 
                    } 
            } 
        } 
    } 
}
