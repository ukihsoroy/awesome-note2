package org.ko.spark.streaming.project.dao

import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.util.Bytes
import org.ko.kafka.project.utils.HBaseUtils
import org.ko.spark.streaming.project.domain.{CourseClickCount, CourseSearchClickCount}

import scala.collection.mutable.ListBuffer

/**
  * 从搜索引擎跳转点击数数据访问层
  */
object CourseSearchClickCountDAO {

  val TABLE_NAME = "course_search_clickcount"
  val CF = "info"
  val qualifer = "click_count"

  /**
    * 保存数据到HBase
    * @param puts
    */
  def save(puts: ListBuffer[CourseSearchClickCount]): Unit ={
    val table = HBaseUtils.getInstance().getTable(TABLE_NAME)
    for (element <- puts) {
      table.incrementColumnValue(Bytes.toBytes(element.day_search_course),
        Bytes.toBytes(CF),
        Bytes.toBytes(qualifer),
        element.click_count
      )
    }
  }

  /**
    * 根据RowKey查询值
    * @param day_search_course
    * @return
    */
  def count(day_search_course: String): Long = {
    val table = HBaseUtils.getInstance().getTable(TABLE_NAME)
    val get = new Get(Bytes.toBytes(day_search_course))
    val value = table.get(get).getValue(CF.getBytes(), qualifer.getBytes())
    if (value == null) {
      0L
    } else {
      Bytes.toLong(value)
    }
  }

  def main(args: Array[String]): Unit = {
    val list = new ListBuffer[CourseSearchClickCount]
    list.append(CourseSearchClickCount("2011111_www.baidu.com_8", 8))
    list.append(CourseSearchClickCount("2011111_cn.bing.com_9", 81))
    save(list)
  }
}
