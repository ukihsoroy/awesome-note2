package org.ko.scala

/**
  * 递归函数
  */
object scala9 {

  //计算n! 递归
  def factorial (n: Int): Int = {
    if (n <= 0) {
      1
    } else {
      n * factorial(n - 1)
    }
  }

  @annotation.tailrec
  def factorial(n: Int, m: Int): Int = {
    if (n <= 0) {
      m //m是个累乘器, m是每个方法累乘的结果 真的优雅
    } else {
      factorial(n - 1, m * n)
    }
  }

  def main(args: Array[String]): Unit = {
    val s1 = factorial(5)
    println(s1)

    val s2 = factorial(5, 1)
    println(s2)
  }
}
