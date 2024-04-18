package org.ko.spark.streaming.kafka

import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Spark Streaming对接Kafka的方式一
  * 192.168.37.131:2181 test kafka-streaming-topic 1
  * 要求运行时kafka版本为0.8
  */
object KafkaReceiverWordCount {

  def main(args: Array[String]): Unit = {

    if (args.length != 4) {
      System.err.println("Usage: KafkaReceiverWordCount <zkQuorum> <group> <topics> <numThreads>")
      System.exit(0)
    }

    val sparkConf = new SparkConf()
      .setAppName("KafkaReceiverWordCount")
      .setMaster("local[2]")

    val Array(zkQuorum, group, topics, numThreads) = args

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val topicMap = topics.split(",").map((_, numThreads.toInt)).toMap

    //TODO... Spark Streaming 如何对接Kafka
    val messages = KafkaUtils.createStream(ssc, zkQuorum, group, topicMap)

    messages
      .map(_._2)
      .flatMap(_.split(" "))
      .map((_, 1))
      .reduceByKey(_ + _)
      .print()

    ssc.start()
    ssc.awaitTermination()


  }

}
