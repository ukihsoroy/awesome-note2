package org.ko.scala

object scala4 {

  def main(args: Array[String]): Unit = {
    //if 表达式
    println("--------if--------")
    val s = if (true) 1 else 2
    println(s)

    val u = if (s != 1) "K.O"
    println(u)

    //for comprehension
    println("-------for---------")
//    for {
//      x <- xs
//      y = x + 1
//      if (y > 0)
//    } yield y

    //定义list
    val l = List("alice", "bob", "cathy")

    for (
      s <- l //generator
    ) println(s)
    println("-------------------")

    for (
      s <- l
      if (s.length > 3) //filter
    ) println(s)
    println("-------------------")

    val result = for {
      s <- l
      x = s.toUpperCase() //variable binding
      if (x != "")
    } yield (x) //generate new collection

    result.foreach(println)
  }
}
