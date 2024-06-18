package org.ko.spark.log

import org.apache.spark.sql.SparkSession

/**
  * 第一步清洗：抽取出我们所需要的指定列的数据
  */
object SparkStatFormatJob {

  def main(args: Array[String]): Unit = {
    System.setProperty("hadoop.home.dir", "D:/software/hadoop-3.1.0/hadoop-3.1.0")
    val spark = SparkSession.builder()
      .appName("SparkStatFormatJob")
      .master("local[2]")
      .getOrCreate()

    val access = spark.sparkContext.textFile("10000_access.log")
//    access.take(10).foreach(println)

    access.map(line => {
      val splits = line.split(" ")
      val ip = splits(0)
      //原始日志的第三个和第四个字段拼接起来就是完整的访问时间：
      //注意：SimpleDateFormat是线程不安全的
      // [10/Nov/2016:00:01:02 +0800] ---> yyyy-MM-dd HH:mm:ss
      val time = splits(3) + " " + splits(4)
      val url = splits(11).replaceAll("\"", "")
      val traffic = splits(9)
//      (ip, DateUtils.parse(time), url, traffic)
      DateUtils.parse(time) + "##" + url + "##" + traffic + "##" + ip
    }).saveAsTextFile("file:/D:/code/java/kayo-repo/spark-sql/output/")


    spark.stop()
  }

}
