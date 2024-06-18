package org.ko.spark

import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row, SparkSession}

/**
  * DataFrame和RDD的互操作
  */
object DataFrameRDDApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DataFrameRDDApp")
      .master("local[2]")
      .getOrCreate()

    //第一种转换方式，反射转换
    inferReflection(spark)

    //第二张转换方式，编程转换
    program(spark)

    spark.close()
  }

  private def inferReflection(spark: SparkSession) = {
    //1. RDD ---> DataFrame
    val rdd = spark.sparkContext.textFile("person.data")

    //2. 注意：需要导入隐式转换
    import spark.implicits._
    val personDF = rdd.map(_.split(","))
      .map(line => Person(line(0).toInt, line(1), line(2).toInt))
      .toDF()

    //3. 调用
    dataFrameAPI(personDF, spark)
  }

  private def program(spark: SparkSession) = {
    //1. RDD ---> DataFrame
    val rdd = spark.sparkContext.textFile("person.data")

    //2. RDD ---> Row
    val personRDD = rdd.map(_.split(","))
      .map(line => Row(line(0).toInt, line(1), line(2).toInt))

    //3. 定义一个StructType
    val structType = StructType(Array(
      StructField("id", IntegerType),
      StructField("name", StringType),
      StructField("age", IntegerType)
    ))

    //4. 创建DataFrame
    val personDF = spark.createDataFrame(personRDD, structType)

    //5. 调用
    dataFrameAPI(personDF, spark)
  }

  def dataFrameAPI(personDF: DataFrame, spark: SparkSession): Unit = {
    //1. 显示表信息
    personDF.show

    //2. 筛选表值并显示
    personDF.filter(personDF.col("age") > 29).show

    //3. 可以创建一张表
    personDF.createOrReplaceTempView("person")

    //4. 使用sql查询
    spark.sql("select * from person where age > 29").show
  }

  case class Person(id: Int, name: String, age: Int)

}
