package org.ko.spark.streaming.project.utils

import java.util.Date

import org.apache.commons.lang3.time.{FastDateFormat, FastDateParser}

/**
  * 日期时间工具类
  */
object DateUtils {

  val YYYYMMDDHHMMSS = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss")
  val TARGE_FORMAT = FastDateFormat.getInstance("yyyyMMddHHmmss")

  def getTime(time:String): Long = {
    YYYYMMDDHHMMSS.parse(time).getTime
  }

  def parseToMinute(time:String): String = {
    TARGE_FORMAT.format(new Date(getTime(time)))
  }


  def main(args: Array[String]): Unit = {
    println(parseToMinute("2017-12-11 12:24:55"))
  }

}
