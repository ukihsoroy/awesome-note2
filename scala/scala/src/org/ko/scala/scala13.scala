package org.ko.scala

/**
  * range 和 stream
  */
object scala13 {

  def main(args: Array[String]): Unit = {
    //1. Range
    val a = 1 to 10
    println(a.toList)

    val b = 1 to 10 by 2
    println(b.toList)

    val c = 1 until 10
    println(c.toList)

    //2. Stream, Stream is a lazy List.
    //只确定第一个值，后面的只有用到才会确定，lazy.
    val s1 = 1 #:: 2 #:: 3 #:: Stream.empty
    println(s1)

    val stream = (1 to 10000000).toStream
    println(stream) //只求第一个值

    val s2 = stream.head
    println(s2) //获取第一个

    val s3 = stream.tail
    println(s3) //只求第一个值
  }
}
