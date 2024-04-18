package org.ko.scala

/**
  * 函数柯里化
  */
object scala8 {

  def add1(x: Int, y: Int) = x + y

  def add2(x: Int)(y: Int) = x + y

  def curriedAdd(a: Int)(b: Int) = a + b

  def main(args: Array[String]): Unit = {
    println(add1(1, 2))
    println(add2(1)_) //其实返回的是一个函数
    println(add2(1)(2))

    //下划线通配后面的参数列表
    val addOne = curriedAdd(1)_ //Int => Int
    addOne(2) //3
  }

}
