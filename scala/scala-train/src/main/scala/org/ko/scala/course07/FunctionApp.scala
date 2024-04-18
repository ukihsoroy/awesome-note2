package org.ko.scala.course07

/**
  * 匿名函数: 函数是可以命名的，也可以不命名
  * (参数名：参数类型) => 函数体
  */
object FunctionApp extends App {

//  def sayHello (name:String): Unit = {
//    println("Hi..." + name)
//  }
//
//  sayHello("K.O")


  //将原来接收两个参数的函数分开
//  def sum (a: Int, b: Int) = a + b
//  println(sum(2, 3))
//
//  def sum2(a:Int)(b:Int) = a + b
//  println(sum2(2)(3))


  val l = List(1, 2, 3, 4, 5, 6, 7, 8)

  //map: 逐个去操作集合中的每个元素
//  l.map(_ + 1).foreach(println)

//  l.map(_ * 2).filter(_ > 8).foreach(println)

  //1+2 3+3 求和
  println(l.reduce(_+_))

  println(l.reduceLeft(_-_))

  println(l.reduceRight(_-_))

  println(l.fold(0)(_-_))

  println(l.foldLeft(0)(_-_))

  println(l.foldRight(0)(_-_))

  val f = List(List(1, 2), List(3, 4), List(5, 6))

  //压扁
  f.flatten

  f.map(_.map(_*2))
  f.flatMap(_.map(_*2))

  val txt = scala.io.Source.fromFile("hello.txt").mkString

  val txts = List(txt)
  //链式编程
  txts.flatMap(_.split(",")).map((_,1)).foreach(println)
}
