package org.ko.scala.course04

object AbstractApp {

  def main(args: Array[String]): Unit = {

//    new Person2 ()

    val student = new Student2
    println(student.name)
    student.speak()
  }
}

/**
  * 类的一个或者多个方法没有完整的实现 （只有定义没有实现）
  *
  */
abstract class Person2 {

  def speak()

  val name: String
  val age: Int
}

class Student2 extends Person2 {
  override def speak(): Unit = println("speak...")

  override val name: String = "K.O"
  override val age: Int = 1
}
