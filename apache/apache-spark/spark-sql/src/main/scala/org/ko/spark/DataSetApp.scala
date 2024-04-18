package org.ko.spark

import org.apache.spark.sql.SparkSession

object DataSetApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DataSetApp")
      .master("local[2]")
      .getOrCreate()

    //spark如何解析csv文件
    //注意: 需要导入隐士转换
    import spark.implicits._
    val df = spark.read.option("header", "true")
      .option("inferSchema", "true")
        .csv("sales.csv")

    df.show

    val ds = df.as[Sales]
    ds.map(line => line.itemId).show

    spark.stop()
  }

  case class Sales(transactionId: Int, customerId: Int, itemId: Int, amountPaid: Double)
}
