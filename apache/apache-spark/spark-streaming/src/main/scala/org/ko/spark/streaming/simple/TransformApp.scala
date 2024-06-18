package org.ko.spark.streaming.simple

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * <p>
  *   黑名单过滤
  * </p>
  */
object TransformApp {

  def main(args: Array[String]): Unit = {
    //1. 创建spark conf配置
    val sparkConf = new SparkConf()
      .setMaster("local[2]")
      .setAppName("NetworkWordCount")

    //2. 创建StreamingContext需要两个参数: SparkConf 和 batch interval
    val ssc = new StreamingContext(sparkConf, Seconds(5))
    val lines = ssc.socketTextStream("192.168.37.128", 6789)

    //3. 构建黑名单
    val blacklist = List("zs", "ls")
    val blackRDD = ssc.sparkContext.parallelize(blacklist)
      .map(x => (x,true))

    /**
      * map(x => (x.split(",")(1), x)) ##将输入数据20180808,zs按逗号拆分取出zs做为key构成元组("zs","20180808,zs")
      * transform ##将DStream转换成RDD
      * rdd.leftOuterJoin(blackRDD) ##两个元组left join后, 数据格式("zs",("20180808,zs",true))
      * filter(x => !x._2._2.getOrElse(false)) ##取出元组._2._2值为false或者空的数据
      * map(x => x._2._1) ##转换成需要的数据格式---->"20180808,zs"
      */
    val result = lines.map(x => (x.split(",")(1), x)).transform{rdd =>
      rdd.leftOuterJoin(blackRDD)
        .filter(x => !x._2._2.getOrElse(false))
        .map(x => x._2._1)
    }

    result.print()

    ssc.start()
    ssc.awaitTermination()
  }
}
