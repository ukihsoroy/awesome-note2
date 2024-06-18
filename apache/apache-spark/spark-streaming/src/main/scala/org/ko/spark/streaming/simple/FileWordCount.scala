package org.ko.spark.streaming.simple

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * <p>
  *   使用Spark Streaming 处理文件系统(local/HDFS)的数据
  * </p>
  */
object FileWordCount {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf()
      .setMaster("local")
      .setAppName("FileWordCount")

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val lines = ssc.textFileStream("D:\\tmp")

    val result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_+_)

    result.print()

    ssc.start()
    ssc.awaitTermination()
  }

}
