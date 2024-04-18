package org.ko.flink

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.StreamExecutionEnvironment
import org.apache.flink.api.scala._
import org.apache.flink.streaming.api.windowing.time.Time

object FlinkScalaExample {

  def main(args: Array[String]): Unit = {
    val port = try {
      ParameterTool.fromArgs(args).getInt("port")
    } catch {
      case e: Exception => {
        println("error: " + e.getMessage)
        return
      }
    }

    // get the execution environment
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment

    // get input data by connection to the socket.
    val text = env.socketTextStream("localhost", port, "\n")

    // parse the data, group it, window it, and aggregate the counts
    val windowCounts = text
      .flatMap(_.split(" "))
      .map(WordWithCount(_, 1))
      .keyBy("word")
      .timeWindow(Time.seconds(5), Time.seconds(1))
      .sum("count")

    windowCounts.print().setParallelism(1)

    env.execute("socket window word count.")
  }

  case class WordWithCount(word: String, count: Long)

}
