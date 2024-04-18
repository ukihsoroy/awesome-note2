package org.ko.spark.streaming.kafka

import kafka.serializer.StringDecoder
import org.apache.spark.SparkConf
import org.apache.spark.streaming.kafka.KafkaUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * Spark Streaming对接Kafka的方式一
  * 192.168.37.131:9092 kafka-streaming-topic
  * 要求运行时kafka版本为0.8Q
  */
object KafkaStreamingApp {

  def main(args: Array[String]): Unit = {

    if (args.length != 2) {
      System.err.println("Usage: KafkaDirectWordCount <brokers> <topics>")
      System.exit(1)
    }

    val sparkConf = new SparkConf()
      .setAppName("KafkaStreamingApp")
      .setMaster("local[2]")

    val Array(brokers, topics) = args

    val ssc = new StreamingContext(sparkConf, Seconds(5))

    val kafkaParams = Map[String, String](
      "metadata.broker.list" -> brokers
    )

    val topicSet = topics.split(",").toSet

    //TODO... Spark Streaming 如何对接Kafka
    val messages = KafkaUtils
      .createDirectStream[String, String, StringDecoder, StringDecoder](ssc, kafkaParams, topicSet)

    messages.map(_._2).count().print()


    ssc.start()
    ssc.awaitTermination()


  }

}
