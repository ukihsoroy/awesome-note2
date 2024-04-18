package org.ko.spark.log.dao

import java.sql.{Connection, PreparedStatement}

import org.ko.spark.log.MySQLUtils
import org.ko.spark.log.model.{DayTrafficsTopNStat, DayVideoAccessStat, DayVideoCityAccessStat}

import scala.collection.mutable.ListBuffer

/**
  * 各个维度统计的DAO操作
  */
object StatDAO {

  /**
    * 批量保存 #DayVideoAccessStat 到数据库
    * @param list
    */
  def insertDayVideoAccessTopN(list: ListBuffer[DayVideoAccessStat]) = {

    var conn: Connection = null
    var statement: PreparedStatement = null

    try {
      conn = MySQLUtils.getConnection()
      conn.setAutoCommit(false) //设置手动提交

      val sql = "INSERT INTO t_day_video_access_topn_stat(day, cms_id, times) values(?, ?, ?)"
      statement = conn.prepareStatement(sql)
      list.foreach(target => {
        statement.setString(1, target.day)
        statement.setLong(2, target.cmsId)
        statement.setLong(3, target.times)

        statement.addBatch()
      })

      statement.executeBatch()  //执行批量处理
      conn.commit() //手动提交

    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(conn, statement)
    }
  }

  def insertDayVideoCityAccessStat(list: ListBuffer[DayVideoCityAccessStat]) = {

    var conn: Connection = null
    var statement: PreparedStatement = null

    try {
      conn = MySQLUtils.getConnection()
      conn.setAutoCommit(false) //设置手动提交

      val sql = "INSERT INTO t_day_video_city_access_topn_stat(day, cms_id, city, times, times_rank) values(?, ?, ?, ?, ?)"
      statement = conn.prepareStatement(sql)
      list.foreach(target => {
        statement.setString(1, target.day)
        statement.setLong(2, target.cmsId)
        statement.setString(3, target.city)
        statement.setLong(4, target.times)
        statement.setInt(5, target.timesRank)

        statement.addBatch()
      })

      statement.executeBatch()  //执行批量处理
      conn.commit() //手动提交

    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(conn, statement)
    }
  }

  //t_day_video_traffics_topn_stat
  def insertDayVideoTrafficsStat(list: ListBuffer[DayTrafficsTopNStat]) = {
    var conn:Connection = null
    var statement:PreparedStatement = null
    try {
      conn = MySQLUtils.getConnection()
      conn.setAutoCommit(false) //设置手动提交

      val sql = "INSERT INTO t_day_video_traffics_topn_stat(day, cms_id, traffics) values(?, ?, ?)"
      statement = conn.prepareStatement(sql)
      list.foreach(target => {
        statement.setString(1, target.day)
        statement.setLong(2, target.cmsId)
        statement.setLong(3, target.traffics)

        statement.addBatch()
      })
      statement.executeBatch()  //执行批量处理
      conn.commit() //手动提交
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(conn, statement)
    }
  }

  /**
    * 删除指定日期的数据
    * @param day
    */
  def deleteData (day:String): Unit = {
    val tables = Array(
      "t_day_video_access_topn_stat",
      "t_day_video_city_access_topn_stat",
      "t_day_video_traffics_topn_stat"
    )

    var conn:Connection = null
    var statement:PreparedStatement = null
    try {
      conn = MySQLUtils.getConnection()
      for (table <- tables) {
        val deleteSQL = s"delete from $table where day = ?"
        statement = conn.prepareStatement(deleteSQL)
        statement.setString(1, day)
        statement.executeUpdate()
      }
    } catch {
      case e: Exception => e.printStackTrace()
    } finally {
      MySQLUtils.release(conn, statement)
    }

  }
}
