package org.ko.scala.course04

/**
  * 通常用在模式匹配里面
  */
object CaseClassApp {

  def main(args: Array[String]): Unit = {
    println(Dog("wangcai").name)
  }
}

/**
  * case class不用new
  * @param name
  */
case class Dog(name: String)
