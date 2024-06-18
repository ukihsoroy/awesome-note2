package org.ko.scala.course05

object ArrayApp extends App {


  //定长数组
//  val a = new Array[String](5)
//  println(a.length)
//  a(1) = "Hello"
//  println(a.mkString(","))
//
//  val b = Array("hadoop", "spark", "storm")
//  println(b(1))
//  b(1) = "flink"
//  println(b(1))
//
//  val c = Array(1, 2, 3, 4, 5, 6, 7, 8, 9)
//  println(c.sum)
//  println(c.min)
//  println(c.max)
//  println(c.mkString(","))
//  println(c.mkString("<", ",", ">"))


  //可变数组
  val c = scala.collection.mutable.ArrayBuffer[Int]()
  c += 1
  c += 2
  c += (3, 4, 5)
  c ++= Array(6, 7, 8)
  c.insert(0, 0)
  c.remove(1)
  c.remove(0, 3)
  c.trimEnd(2) //456
  println(c)
//  println(c.toArray.mkString(","))

  for (i <- 0 until c.length) {
    println(c(i))
  }

  for (ele <- c) {
    println(ele)
  }

  for (i <- (0 until c.length).reverse) {
    println(c(i))
  }

}
