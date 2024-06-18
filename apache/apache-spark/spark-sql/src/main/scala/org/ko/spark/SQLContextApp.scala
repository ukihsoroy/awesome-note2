package org.ko.spark

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext

/**
  * SQLContext的使用
  * 注意IDEA是在本地, 而测试环境实在服务器，能不能再本地测试呢
 */
object SQLContextApp {

  def main(args: Array[String]): Unit = {

    val path = args(0)

    //1. 创建相应的Context
    val conf = new SparkConf()
//    conf.setAppName("SQLContextApp").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    //2. 相关处理:
    val people = sqlContext.read.format("json").load(path)
//    val people = sqlContext.read.format("json").load("people.json")
    people.printSchema()
    people.show()

    //3. 关闭资源
    sc.stop()
  }
}
