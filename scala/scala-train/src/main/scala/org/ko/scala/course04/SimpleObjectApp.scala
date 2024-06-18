package org.ko.scala.course04

object SimpleObjectApp {

  def main(args: Array[String]): Unit = {
    val people = new People()
    people.name = "Messi"
//    people.age = 30
    println(people.name + ".." + people.age)

    println("invoke eat method: " + people.eat())

    people.watchFootBall("Barcelona")

    people.printInfo()

//    println(people.gender)
  }

}

class People {

  //定义属性
  /**
    * 自动生成get set
    */
  var name: String = _ //_ 占位符

  /**
    * 常量，自动生成get
    */
  val age: Int = 10

  private [this] val gender = "male"

  def printInfo(): Unit = {
    println("gender: " + gender)
  }

  def eat(): String = {
    name + " eat..."
  }

  def watchFootBall(teamName: String): Unit = {
    println(name + " is watching match of " + teamName + ".")
  }
}

