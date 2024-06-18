package org.ko.scala.course05

object ListApp extends App {

  val l = List(1, 2, 3, 4, 5)

  l.head //第一个
  l.tail //除了第一个

  // head :: tail
  val l2 = 1 :: Nil

  val l3 = 2 :: l2

  val l4 = 1 :: 2 :: 3 :: Nil

  //可变集合
  val l5 = scala.collection.mutable.ListBuffer[Int]()

  l5 += 2
  l5 += (3, 4, 5)
  l5 ++= List(6, 7, 8, 9)

  l5 -= 2
  l5 -= 3
  l5 -= (1, 4)
  l5 --= List(5, 6, 7, 8)
  println(l5)
  println(l5.toList)
  println(l5.toArray)

  l5.isEmpty
  l5.tail

  def sum(nums:Int*): Int = {
    if (nums.isEmpty) {
      0
    } else {
      //:_* 把seq 转换成 可变参数
      nums.head + sum(nums.tail:_*)
    }
  }

  println(sum(1, 2, 3, 4))

}
