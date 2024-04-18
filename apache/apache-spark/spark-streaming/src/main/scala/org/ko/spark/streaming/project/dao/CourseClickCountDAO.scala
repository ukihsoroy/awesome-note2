package org.ko.spark.streaming.project.dao

import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.util.Bytes
import org.ko.kafka.project.utils.HBaseUtils
import org.ko.spark.streaming.project.domain.CourseClickCount

import scala.collection.mutable.ListBuffer

/**
  * 实战课程点击数数据访问层
  */
object CourseClickCountDAO {

  val TABLE_NAME = "course_clickcount"
  val CF = "info"
  val qualifer = "click_count"

  /**
    * 保存数据到HBase
    * @param puts
    */
  def save(puts: ListBuffer[CourseClickCount]): Unit ={
    val table = HBaseUtils.getInstance().getTable(TABLE_NAME)
    for (element <- puts) {
      table.incrementColumnValue(Bytes.toBytes(element.day_course),
        Bytes.toBytes(CF),
        Bytes.toBytes(qualifer),
        element.click_count
      )
    }
  }

  /**
    * 根据RowKey查询值
    * @param day_course
    * @return
    */
  def count(day_course: String): Long = {
    val table = HBaseUtils.getInstance().getTable(TABLE_NAME)
    val get = new Get(Bytes.toBytes(day_course))
    val value = table.get(get).getValue(CF.getBytes(), qualifer.getBytes())
    if (value == null) {
      0L
    } else {
      Bytes.toLong(value)
    }
  }

  def main(args: Array[String]): Unit = {
    val list = new ListBuffer[CourseClickCount]
    list.append(CourseClickCount("2011111_8", 8))
    list.append(CourseClickCount("2011111_9", 81))
    list.append(CourseClickCount("2011111_1", 812))
    list.append(CourseClickCount("2011111_6", 18))
    save(list)
  }
}
