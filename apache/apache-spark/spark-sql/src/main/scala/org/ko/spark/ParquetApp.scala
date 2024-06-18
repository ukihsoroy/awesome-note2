package org.ko.spark

import org.apache.spark.sql.SparkSession

object ParquetApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SparkSessionApp")
      .master("local[2]")
      .getOrCreate()

    val df = spark.read.format("parquet").load("student.parquet")
    df.write.format("parquet").save("student.parquet")

    //不指定格式 默认是parquet
    spark.read.load("student.parquet")
//    spark.read.load("student.json")

  }
}
