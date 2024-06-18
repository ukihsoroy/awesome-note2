package org.ko.spark

import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Spark Hive SQL的使用
  * 使用时需要通过--jars 把Mysql的驱动传递到classpath里面
  */
object HiveContextApp {

  def main(args: Array[String]): Unit = {

    //1. 创建相应的Context
    val conf = new SparkConf()
    //    conf.setAppName("HiveContextApp").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val hiveContext = new HiveContext(sc)

    //2. 相关处理:
    val people = hiveContext.table("tmp").show

    //3. 关闭资源
    sc.stop()
  }

}
