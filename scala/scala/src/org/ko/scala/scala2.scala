package org.ko.scala

object scala2 {

  def main(args: Array[String]): Unit = {
    val a:Byte = 10
    val b:Short = 11
    val c:Int = 12
    val d:Long = 13
    val e:Float = 14
    val f:Double = 15.5
    val g:Boolean = true
    val h:Char = 'h'
    val i:Unit = ()

    //字符串, 就是Java的字符串
    val j:String = "K.O"

    //字符串插值
    println(s"my name is ${j}!")

  }
}
