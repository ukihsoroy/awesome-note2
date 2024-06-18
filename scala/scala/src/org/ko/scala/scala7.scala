package org.ko.scala

/**
  * 匿名函数
  */
object scala7 {

  def main(args: Array[String]): Unit = {
    //匿名函数
    val add = (x: Int, y: Int) => x + y
    println(add(1, 2))
  }
}
