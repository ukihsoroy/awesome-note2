package org.ko.spark

import org.apache.spark.sql.SparkSession

object SchemaInferApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("SchemaInferApp")
      .master("local[2]")
      .getOrCreate()

    val df = spark.read.format("json").load("people.json")
    df.printSchema()
    df.show()

    spark.stop()
  }

}
