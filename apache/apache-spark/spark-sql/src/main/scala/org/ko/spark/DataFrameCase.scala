package org.ko.spark

import org.apache.spark.sql.SparkSession

/**
  * DataFrame中的其他操作
  */
object DataFrameCase {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DataFrameCase")
      .master("local[2]")
      .getOrCreate()

    //1. 获取student RDD
    val studentRDD = spark.sparkContext.textFile("student.data")

    //2. RDD ---> DataFrame
    import spark.implicits._
    val studentDF = studentRDD.map(_.split("\\|"))
      .map(line => Student(line(0).toInt, line(1), line(2), line(3)))
      .toDF()

    //3. 操作DataFrame
    //只显示20条, 并且长度会截取
    studentDF.show
    studentDF.show(30)
    studentDF.show(30, truncate = false)

    //返回前10行
    studentDF.take(10).foreach(println)
    println(studentDF.first)
    studentDF.head(3).foreach(println)

    //限制返回结果
    studentDF.select("email").show(30, truncate = false)

    //获取名字为空的数据
    studentDF.filter("name = ''").show

    //获取名字为空或者为null的数据
    studentDF.filter("name = '' OR name = 'NULL'").show

    //name以M开头的人
    studentDF.filter("SUBSTR(name, 0, 1) = 'M'").show

    //查看所有spark sql函数
    spark.sql("show functions").show

    //排序
    studentDF.sort(studentDF("name")).show(30)
    studentDF.sort(studentDF("name").desc).show(30)
    studentDF.sort("name", "id").show(30)
    studentDF.sort(studentDF("name").asc, studentDF("id").desc).show(30)

    //对列重命名
    studentDF.select(studentDF("name").as("student_name")).show

    //join
    val studentDF2 = studentRDD.map(_.split("\\|"))
      .map(line => Student(line(0).toInt, line(1), line(2), line(3)))
      .toDF()

    //join 默认是inner join, 后面条件一定是===三个等号
    studentDF.join(studentDF2,
      studentDF.col("id") === studentDF2.col("id")).show

  }

  case class Student(id: Int, name: String, phone: String, email: String)
}
