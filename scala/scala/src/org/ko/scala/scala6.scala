package org.ko.scala

/**
  * 高阶函数
  */
object scala6 {
  def foo1 (x: Int) = x

  def foo2 (x: => Int) = x

  def test1(x: Int, y: Int): Int = x * x

  def test2(x: => Int, y: => Int): Int = x * x

  def bar (x: Int, y: => Int): Int = 1

  def loop (): Int = loop()

  def operate (f: (Int, Int) => Int) = {
    f (4, 4)
  }

  def greeting() = (name: String) => {s"Hello, ${name}"}


  def main(args: Array[String]): Unit = {
    val a = test1(3 + 4, 8)
    val b = test2(3 + 4, 8)

    println(s"a = ${a}")
    println(s"b = ${b}")

    bar(1, loop()) //不会进入loop递归
//    bar(loop(), 1) //会进入loop递归
  }
}
