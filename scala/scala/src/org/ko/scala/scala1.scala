package org.ko.scala

object scala1 {

  def main(args: Array[String]): Unit = {
    //1. val 定义 immutable variable
    val x = 10
    val y: Int = 11
    println(x + y)
//    x = 12 再赋值会报错

    //2. var 定义 mutable variable
    var a = "K.O"
    a = "kayo"
    println(a)

    //3. lazy val
    lazy val b = x * y
    //只有当使用的时候才会真正复制
    println(b)

    //*可以不显示指定变量的类型，因为Scala会自动进行类型推导
  }

}
