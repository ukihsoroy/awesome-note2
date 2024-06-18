package org.ko.scala

/**
  * 基本function
  */
object scala3 {

  def main(args: Array[String]): Unit = {
    var s = hello1("K.O")
    println(s)

    s = hello2("kayo")
    println(s)

    val n = add(1, 2)
    println(n)
  }

  def hello1 (name: String): String = {
    s"hello, my name is ${name}!"
  }

  def hello2 (name: String)= {
    s"hello, my name is ${name}!"
  }

  def add (a: Int, b: Int) = a + b
}
