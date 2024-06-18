package org.ko.scala

/**
  * 高阶, 柯里化, 递归例子
  * 求 f(x) 的和 a - b 区间
  */
object scala10 {


  def sum (f: Int => Int) (a: Int) (b: Int): Int = {
    @annotation.tailrec
    def loop(n: Int, acc: Int): Int = {
      if (n > b) {
        println(s"n=${n}, acc=${acc}")
        acc
      } else {
        println(s"n=${n}, acc=${acc}")
        loop(n + 1, acc + f(n))
      }
    }
    loop(a, 0)
  }

  def main(args: Array[String]): Unit = {
    val s = sum(x => x)(1)(5)
    println(s)

    val r = sum(x => x * x)(1)(5)
    println(r)

    val z = sum(x => x * x * x)(1)(5)
    println(z)

    val sumSquare = sum(x => x * x )_
    val y = sumSquare(1)(5)
    println(y)

  }

}


