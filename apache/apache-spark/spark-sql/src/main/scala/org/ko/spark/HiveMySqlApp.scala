package org.ko.spark

import org.apache.spark.sql.SparkSession

/**
  * 使用Hive和Mysql综合查询数据
  */
object HiveMySqlApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("HiveMySqlApp")
      .master("local[2]")
      .getOrCreate()

    //1. 加载Hive表的数据
    val hiveDF = spark.table("student")

    //2. 加载Mysql表数据
    val mysqlDF = spark.read.format("jdbc")
        .option("url", "jdbc:mysql://localhost:3306")
        .option("dbtable", "spark.DEPT")
        .option("user", "root")
        .option("password", "tiger")
        .option("driver", "com.mysql.jdbc.Driver").load

    //3. JOIN
    val resultDF = hiveDF.join(mysqlDF,
      hiveDF.col("dept_no") === mysqlDF.col("id"))

    resultDF.show()


    spark.stop()
  }
}
