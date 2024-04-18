package org.ko.spark.log

import org.apache.spark.sql.{SaveMode, SparkSession}

/**
  * 使用Spark完成我们的数据清洗操作
  */
object SparkStatCleanJob {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:/software/hadoop-3.1.0/hadoop-3.1.0")
    val spark = SparkSession.builder()
      .appName("SparkStatCleanJob")
      .master("local[2]")
      .getOrCreate()

    //读取access file
    val accessRDD = spark.sparkContext.textFile("access.log")
//    accessRDD.take(10).foreach(println)

    //RDD ---> DF;
    //1. row 方式
//    val accessDF = spark.createDataFrame(accessRDD.map(AccessConvertUtils.parseLog), AccessConvertUtils.struct)

    //2. cass class方式
    import spark.implicits._
    val accessDF = accessRDD.map(AccessConvertUtils.parseLogByCaseClass).toDF()

//    accessDF.printSchema()
//    accessDF.show(false)
//    coalesce: 指定返回文件的数量，1是单文件
    accessDF.coalesce(1).write.format("parquet")
      .mode(SaveMode.Overwrite)
      .partitionBy("day").save("file:/D:/code/java/kayo-repo/spark-sql/clean/")
    spark.stop()
  }

}
