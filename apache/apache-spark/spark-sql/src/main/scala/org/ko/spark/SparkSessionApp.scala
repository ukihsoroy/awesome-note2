package org.ko.spark

import org.apache.spark.sql.SparkSession

/**
  * Spark Session的使用
  *
  */
object SparkSessionApp {

  def main(args: Array[String]): Unit = {

    val spark = SparkSession.builder()
      .appName("SparkSessionApp")
      .master("local[2]")
      .getOrCreate()

    val people = spark.read.json("people.json")

    people.show
  }
}
