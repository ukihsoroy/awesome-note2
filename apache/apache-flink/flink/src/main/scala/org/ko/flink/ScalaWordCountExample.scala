package org.ko.flink

import org.apache.flink.api.scala.ExecutionEnvironment

import org.apache.flink.api.scala._

object ScalaWordCountExample {

  def main(args: Array[String]): Unit = {
    //初始化环境
    val env = ExecutionEnvironment.getExecutionEnvironment

    //从字符串中加载数据
    val text = env.fromElements(
      "i love three things in the world",
      "the sun",
      "the moon",
      "and you"
    )

    //分割字符串，汇总tuple，按照key进行分组、统计分组后的word个数
    val counts = text
      .flatMap(_.toLowerCase().split(" ").filter(_.nonEmpty))
      .map((_, 1))
      .groupBy(0)
      .sum(1)

    //打印
    counts.print()
  }

}
