package org.ko.scala.course08

object ExceptionApp extends App {
  // scalastyle:off println
  try {
    val i = 10 / 0
    println(i)
  } catch {
    case e: ArithmeticException => println("除数不能为0")
    case e: Exception => println(e.getMessage)
  } finally {
    // 释放资源，一定能执行
  }

  // IO
  val file = "text file"

  // scalastyle:on println
}
