package org.ko.spark.log

import org.apache.commons.lang3.StringUtils
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{LongType, StringType, StructField, StructType}

/**
  * 访问日志转换(输入-->输出)
  */
object AccessConvertUtils {

  //定义输出字段
  val struct = StructType(
    Array(
      StructField("url", StringType),
      StructField("cmsType", StringType),
      StructField("cmsId", LongType),
      StructField("traffic", LongType),
      StructField("ip", StringType),
      StructField("city", StringType),
      StructField("time", StringType),
      StructField("day", StringType)
    )
  )

  /**
    * 根据输入的每一行信息转换成输入的样式
    * @param log
    */
  def parseLog(log: String): Row = {
    try {
      val splits = log.split("##")
      val url = splits(1)
      val traffic = splits(2).toLong
      val ip = splits(3)

      val domain = "http://www.imooc.com/"
      val urlIndex = url.indexOf(domain)
      var cmsType = ""
      var cmsId = 0l
      if (urlIndex != -1) {
        val cms = url.substring(url.indexOf(domain) + domain.length)
        val cmsTypeId = cms.split("/")
        if (cmsTypeId.length > 1) {
          cmsType = cmsTypeId(0)
          cmsId = cmsTypeId(1).toLong
        }
      }

      val city = ""
      val time = splits(0)
      val day = time.substring(0, 10).replaceAll("-", "")

      Row(url, cmsType, cmsId, traffic, ip, city, time, day)
    } catch {
      case e: Exception =>
        println("-----AccessConvertUtils.parseLog---->", e.getMessage)
        Row()
    }
  }

  def parseLogByCaseClass (log: String): Access = {
//    try {
      val splits = log.split("##")
      val url = splits(1)
      val traffic = splits(2).toLong
      val ip = splits(3)

      val domain = "http://www.imooc.com/"
      val urlIndex = url.indexOf(domain)
      var cmsType = ""
      var cmsId = 0l
      if (urlIndex != -1) {
        val cms = url.substring(url.indexOf(domain) + domain.length)
        val cmsTypeId = cms.split("/")
        if (cmsTypeId.length > 1) {
          cmsType = cmsTypeId(0)
          if (StringUtils.isNumeric(cmsTypeId(1))) {
            cmsId = cmsTypeId(1).toLong
          }
        }
      }

      val city = IpUtils.getCity(ip)
      val time = splits(0)
      val day = time.substring(0, 10).replaceAll("-", "")

      Access(url, cmsType, cmsId, traffic, ip, city, time, day)
//    } catch {
//      case e: Exception =>
//        println("-----AccessConvertUtils.parseLog---->", e.getMessage)
//        null
//    }
  }

  case class Access(
                     url: String,
                     cmsType: String,
                     cmsId: Long,
                     traffic: Long,
                     ip: String,
                     city: String,
                     time: String,
                     day: String
                   )

}
