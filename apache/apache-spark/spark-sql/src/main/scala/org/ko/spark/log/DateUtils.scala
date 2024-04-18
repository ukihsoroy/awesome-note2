package org.ko.spark.log

import java.text.SimpleDateFormat
import java.util.{Date, Locale}

import org.apache.commons.lang3.time.FastDateFormat

/**
  * 日期处理工具类
  */
object DateUtils {

  //输入文件日期时间格式：[10/Nov/2016:00:01:02 +0800]
  val TIME_FORMAT = FastDateFormat.getInstance("[dd/MMM/yyyy:HH:mm:ss Z]", Locale.ENGLISH)

  //目标日期格式：yyyy-MM-dd HH:mm:ss
  val TARGET_FORMAT = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")

  /**
    * 获取时间：yyyy-MM-dd HH:mm:ss
    * @param time
    * @return
    */
  def parse (time: String): String = {
    try {
      TARGET_FORMAT.format(new Date(getTime(time)))
    } catch {
      case e: Exception =>
        println("---DateUtils.parse--->" + e.getMessage)
        ""
    }
  }

  /**
    * 获取输入日志时间： long类型
    */
  def getTime (time: String): Long = {

    try {
      TIME_FORMAT.parse(time).getTime
    } catch {
      case e: Exception =>
        println("---DateUtils.getTime--->" + e.getMessage)
        0l
    }
  }

  def main(args: Array[String]): Unit = {
    println(parse("[10/Nov/2016:00:01:02 +0800]"))
  }

}


