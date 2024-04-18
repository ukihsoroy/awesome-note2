package org.ko.scala.course05

/**
  * 数据不重复
  */
object SetApp extends App {

  val set = Set(1, 2, 3, 3, 5)

  println(set.mkString(","))

  val s2 = scala.collection.mutable.Set[Int]()
  s2 += 1
  println(s2.size)
}
