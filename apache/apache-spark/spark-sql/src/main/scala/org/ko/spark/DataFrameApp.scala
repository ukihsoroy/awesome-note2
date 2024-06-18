package org.ko.spark

import org.apache.spark.sql.SparkSession

/**
  * DataFrame API基本操作
  */
object DataFrameApp {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder()
      .appName("DataFrameApp")
      .master("local[2]")
      .getOrCreate()

    //1. 将json文件加载成DataFrame
    val peopleDF = spark.read.json("people.json")

    //2. 输出DataFrame对应的schema信息
    peopleDF.printSchema()

    //3. 输出DataFrame 前20条内容
    peopleDF.show

    //4. 只输出name字段
    peopleDF.select("name").show

    //5. 查询某几列所有的数据, 并对列进行计算: select name, age + 10 from table;
    peopleDF.select(peopleDF.col("name"),
      (peopleDF.col("age") + 10).as("age")).show

    //6 根据某一列的值进行过滤: select * from table where age > 25;
    peopleDF.filter(peopleDF.col("age") > 25).show

    //7. 根据某一列进行分组, 然后再进行聚合操作: select age, count(1) from table group by age;
    peopleDF.groupBy("age").count().show

    spark.stop()
  }

}
