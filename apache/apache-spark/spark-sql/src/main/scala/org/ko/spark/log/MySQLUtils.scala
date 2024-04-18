package org.ko.spark.log

import java.sql.{Connection, DriverManager, PreparedStatement}

/**
  * MySql工具类
  */
object MySQLUtils {

  /**
    * 获取数据库连接
    */
  def getConnection(): Connection = {
    DriverManager.getConnection("jdbc:mysql:///log_stat?user=root&password=tiger")
  }

  /**
    * 释放数据库连接等资源
    * @param conn
    * @param statement
    */
  def release (conn: Connection, statement: PreparedStatement): Unit = {
    try {
      if (statement != null) {
        statement.close()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      if (conn != null) {
        conn.close()
      }
    }
  }

  def main(args: Array[String]): Unit = {
    println(getConnection())
  }

}
