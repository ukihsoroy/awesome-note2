package org.ko.spark.log

import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._
import org.ko.spark.log.dao.StatDAO
import org.ko.spark.log.model.{DayTrafficsTopNStat, DayVideoAccessStat, DayVideoCityAccessStat}

import scala.collection.mutable.ListBuffer

/**
  * Top N Spark统计作业
  */
object TopNStatJob {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:/software/hadoop-3.1.0/hadoop-3.1.0")
    val spark = SparkSession.builder()
      .appName("TopNStatJob")
      .config("spark.sql.sources.partitionColumnTypeInference.enabled", "false")
      .master("local[2]")
      .getOrCreate()

    val accessDF = spark.read.format("parquet").load("file:/D:/code/java/kayo-repo/spark-sql/clean/")

//    accessDF.printSchema()
//    accessDF.show(false)
    val day = "20161110";
    StatDAO.deleteData(day)

    //最受欢迎的TopN课程
    videoAccessTopNStat(spark, accessDF, day)

    //按照地市进行统计TopN课程
    cityAccessTopNStat(spark, accessDF, day)

    //按照流量进行统计
    videoTrafficsTopNStat(spark, accessDF, day)

    spark.stop()
  }

  /**
    * 最受欢迎的TopN课程
    * @param spark
    * @param accessDF
    */
  def videoAccessTopNStat(spark: SparkSession, accessDF: DataFrame, day:String) = {
    import spark.implicits._
    //1. 分区裁剪
    val videoAccessTopDF = accessDF.filter($"day" === day && $"cmsType" === "video")
      .groupBy("day", "cmsId").agg(count("cmsId").as("times"))
      .orderBy($"times".desc)
    videoAccessTopDF.show(false)

//    accessDF.createOrReplaceTempView("access_logs")
//    val videoAccessTopDF = spark.sql("" +
//      "SELECT " +
//      " day, " +
//      " cmsId, " +
//      " COUNT(cmsId) AS times " +
//      "FROM access_logs " +
//      "WHERE day = '20161110' " +
//      " AND cmsType = 'video' " +
//      "GROUP BY day, cmsId " +
//      "ORDER BY times DESC")
//    videoAccessTopDF.show(false)

    //将计算结果写入到MySql数据库
    try {
      videoAccessTopDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayVideoAccessStat]

        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val cmsId = info.getAs[Long]("cmsId")
          val times = info.getAs[Long]("times")
          list.append(DayVideoAccessStat(day, cmsId, times))
        })

        StatDAO.insertDayVideoAccessTopN(list)

      })
    } catch {
      case e: Exception => e.printStackTrace()
    }

  }

  def cityAccessTopNStat(spark: SparkSession, accessDF: DataFrame, day:String) = {
    import spark.implicits._
    val cityAccessTopDF = accessDF.filter($"day" === day && $"cmsType" === "video")
      .groupBy("day", "city", "cmsId")
      .agg(count("cmsId").as("times"))
          .orderBy($"times".desc)
//    cityAccessTopDF.show(false)
    val cityTop3DF = cityAccessTopDF.select(
      cityAccessTopDF("day"),
      cityAccessTopDF("city"),
      cityAccessTopDF("cmsId"),
      cityAccessTopDF("times"),
      row_number().over(Window.partitionBy(cityAccessTopDF("city"))
        .orderBy(cityAccessTopDF("times").desc)).as("times_rank"))
      .filter("times_rank <= 3")

    //将计算结果写入到MySql数据库
    try {
      cityTop3DF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayVideoCityAccessStat]

        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val cmsId = info.getAs[Long]("cmsId")
          val city = info.getAs[String]("city")
          val times = info.getAs[Long]("times")
          val timesRank = info.getAs[Int]("times_rank")
          list.append(DayVideoCityAccessStat(day, cmsId, city, times, timesRank))
        })

        StatDAO.insertDayVideoCityAccessStat(list)

      })
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

  def videoTrafficsTopNStat (spark: SparkSession, accessDF: DataFrame, day:String) = {
    import spark.implicits._
    val trafficsTopDF = accessDF.filter($"day" === day && $"cmsType" === "video")
      .groupBy("day", "cmsId")
      .agg(sum("traffic").as("traffics"))
      .orderBy($"traffics".desc)

    //将计算结果写入到MySql数据库
    try {
      trafficsTopDF.foreachPartition(partitionOfRecords => {
        val list = new ListBuffer[DayTrafficsTopNStat]

        partitionOfRecords.foreach(info => {
          val day = info.getAs[String]("day")
          val cmsId = info.getAs[Long]("cmsId")
          val traffics = info.getAs[Long]("traffics")
          list.append(DayTrafficsTopNStat(day, cmsId, traffics))
        })

        StatDAO.insertDayVideoTrafficsStat(list)

      })
    } catch {
      case e: Exception => e.printStackTrace()
    }
  }

}
