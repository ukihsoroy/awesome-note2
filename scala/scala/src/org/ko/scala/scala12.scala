package org.ko.scala

/**
  * list 高级函数
  */
object scala12 {

  def main(args: Array[String]): Unit = {
    //1. filter
    val a = List(1, 2, 3, 4, 5)
    val b = a.filter(x => x % 2 == 1)
    println(b)

    //过滤结果
    val c = "99 Red Balloons".toList.filter(x => Character.isDigit(x))
    println(c)

    //2. takeWhile
    //获取元素 直到x != B返回false 才终止
    val d = "99 Red Balloons".toList.takeWhile(x => x != 'B')
    println(d)

    //3. map函数
    val x = List("x", "y", "z")
    val y = x.map(x => x.toUpperCase)
    println(y)
    val z = x.map(_.toUpperCase)
    println(z)

    //4. 归约操作 reduceLeft(op: (T, T) => T)
    val n = List(1, 2, 3)
    val s1 = n.reduceLeft((x, y) => x + y)
    println(s1)
    val s2 = n.reduceLeft(_ + _)
    println(s2)

    //foldLeft(z: U)(op: (U, T) => U)
    val s3 = n.foldLeft(0)(_ + _)
    println(s3)

    val s4 = n.foldLeft(1)(_ * _)
    println(s4)


  }

}
