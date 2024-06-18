package org.ko.scala

object scala5 {

  def main(args: Array[String]): Unit = {
    //try cache
    try {
      Integer.parseInt("dog")
    } catch {
      case _ => 0
    } finally {
      println("always be printed.")
    }

    //exp match
    val code = 3
    val result = code match {
      case 1 => "one"
      case 2 => "two"
      case _ => "others"
    }
    println(result)


  }
}
