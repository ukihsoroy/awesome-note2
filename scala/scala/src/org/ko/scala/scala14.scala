package org.ko.scala

/**
  * Tuple 元组
  * 就是数据表组的概念
  */
object scala14 {

  def main(args: Array[String]): Unit = {
    val a = (1, 2)
    println(a)

    val b = 1 -> 2
    println(b)

    val c = (1, "K.O", "Math", "95")
    println(c)

    val no = c._1
    val name = c._2
    println(s"序号: ${no}, 名字: ${name}")

    val d = sumSq(List(1, 2, 3))
    println(d)
  }

  //求list长度, 和, 平方和
  def sumSq(in: List[Int]): (Int, Int, Int) = {
    in.foldLeft(0, 0, 0)((t, v) => (t._1 + 1, t._2 + v, t._3 + v * v))
  }
}
