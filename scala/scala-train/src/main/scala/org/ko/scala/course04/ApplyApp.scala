package org.ko.scala.course04

/**
  *
  */
object ApplyApp {
  def main(args: Array[String]): Unit = {
//    for (i <- 1 to 10) {
//      ApplyTest.incr
//    }
//    println(ApplyTest.count) //10 object本身就是一个单例对象

    //类名() 调用object里的apply方法
    val b = ApplyTest() //===> Object.apply, 默认走的Object里的apply

    println("~~~~~~~~~")

    val c = new ApplyTest()
    println(c)

    //对象() 调用class里的apply方法
    c()

  }
}

/**
  * 伴生类和伴生对象
  * 如果有一个class, 还有一个与class同名的object
  * 那么就称为这个object是class的伴生对象， class是object的伴生类
  */
class ApplyTest {

  def apply(): ApplyTest = {
    println("class ApplyTest apply...")
    new ApplyTest
  }
}

object ApplyTest {

  println("object ApplyTest enter...")

  var count = 0

  def incr = {
    count = count + 1
  }

  //最佳实践： 在Object的apply方法中去new Class
  def apply(): ApplyTest = {
    println("Object ApplyTest apply...")

    //在object里去new Class
    new ApplyTest
  }

  println("object ApplyTest leave...")

}
