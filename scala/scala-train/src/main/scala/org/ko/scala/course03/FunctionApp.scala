package org.ko.scala.course03

object FunctionApp {

  def main(args: Array[String]): Unit = {
    //1. 基本用法
//    println(add(2, 3))
//    println(three())
//    sayHello()

    //2. 默认参数
//    sayName()
//    sayName("Sultan")

    //spark
//    loadConf()
//    loadConf("spark-production.conf")

    //3. 命名参数
//    println(speed(100, 10))
    //通过名字识别
//    println(speed(time = 10, distance = 100))

    //4. 可变参数
//    println(sum(1, 2))
//    println(sum(1, 2, 3))
//    println(sum2(1, 2, 3, 4))

    //5. 条件表达式
//    val x = 1
//    val a = if (x > 0) true else false
//    println(a)

    //6. 循环表达式
//    1 to 10
//    1.to(10)
//    Range(1, 10, 0)
//    1.until(10)

//    for (i <- 1 to 10 if i % 2 == 0) {
//      println(i)
//    }

//    val courses = Array("Hadoop", "Spark SQL", "Spark Streaming", "Storm", "Scala")

//    for (course <- courses) {
//      println(course)
//    }

    //course就是courses里面的每个元素
    //=> 就是将左边的course作用上一个函数，变成另一个结果
    //println 就是作用到course上的一个函数
//    courses.foreach(course => println(course))

    var (num, sum) = (100, 0)
    while (num > 0) { //出口
      sum = sum + num
      num = num - 1   //步长
    }
    println(sum)

  }

  def add(x: Int, y: Int): Int = {
    x + y
  }

  def three (): Int = 1 + 2

  def sayHello(): Unit = {
    println("say Hello...")
  }

  def sayName(name: String = "K.O"): Unit = {
    println(name)
  }

  def loadConf(conf: String = "spark-defaults.conf"): Unit = {
    println(conf)
  }

  def speed(distance: Float, time: Float): Float = {
    distance / time
  }

  def sum (a: Int, b: Int): Int = {
    a + b
  }

  def sum (a: Int, b: Int, c: Int): Int = {
    a + b + c
  }

  def sum2 (numbers: Int*): Int = {
    var sum = 0
    for (number <- numbers) {
      sum += number
    }
    sum
  }
}
