package org.ko.spark.log

import com.ggstar.util.ip.IpHelper

/**
  * IP解析工具类
  */
object IpUtils {

  def getCity (ip: String): String = {
    IpHelper.findRegionByIp(ip)
  }

  def main(args: Array[String]): Unit = {
    println(getCity("120.197.202.48"))
  }
}
