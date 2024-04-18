package org.ko.spark

import java.sql.DriverManager

/**
  * 通过JDBC的方式访问
  */
object SparkSQLThriftServerApp {

  def main(args: Array[String]): Unit = {
    Class.forName("org.apache.hive.jdbc.HiveDriver")
    val conn = DriverManager.getConnection("", "", "")
    val ps = conn.prepareStatement("select * from table")
    val rs = ps.executeQuery()
    while (rs.next()) {
      println(s"")
    }

    rs.close()
    ps.close()
    conn.close()
  }
}
