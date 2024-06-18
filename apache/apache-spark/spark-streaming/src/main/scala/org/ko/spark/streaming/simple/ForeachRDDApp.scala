package org.ko.spark.streaming.simple

import java.sql.{Connection, DriverManager}

import org.apache.spark.SparkConf
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * <p>
  *  使用Spark Streaming完成有状态统计，并存到mysql中
  * </p>
  */
object ForeachRDDApp {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[2]").setAppName("StatefulWordCount")
    val ssc = new StreamingContext(sparkConf, Seconds(5))


    val lines = ssc.socketTextStream("192.168.37.128", 6789)

    val result = lines.flatMap(_.split(" ")).map((_, 1)).reduceByKey(_ + _)

//    -----error------Task not serializable
//    result.foreachRDD(rdd => {
//      val conn = createConnection()
//      rdd.foreach { record =>
//        val sql = "INSERT INTO wordcount(word, count) " +
//          "VALUES('" + record._1 + "', '" + record._2 + "')"
//        conn.createStatement().execute(sql)
//      }
//    })

    result.print()

    result.foreachRDD {rdd =>
      rdd.foreachPartition {partitionOfRecords =>
        if (partitionOfRecords.nonEmpty) {
          val conn = createConnection()
          partitionOfRecords.foreach {pair =>
            val validatorSql = "SELECT * FROM wordcount where word = '" + pair._1 + "'"
            val resultSet = conn.createStatement().executeQuery(validatorSql)
            var sql = ""
              if (resultSet.next()) {
              val count = resultSet.getInt(2)
              sql = "UPDATE wordcount SET count = " + (count + pair._2) + " WHERE word = '" + pair._1 + "'"
            } else {
              sql = "INSERT INTO wordcount(word, count) VALUES('" + pair._1 + "', '" + pair._2 + "')"
            }
            conn.createStatement().execute(sql)

          }
          conn.close()
        }

      }
    }
    ssc.start()
    ssc.awaitTermination()
  }

  def createConnection (): Connection = {
    Class.forName("com.mysql.jdbc.Driver")
    DriverManager.getConnection("jdbc:mysql:///spark_stream", "root", "tiger")
  }

}
