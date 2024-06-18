package org.ko.scala.course04

object ConstructorApp {

  def main(args: Array[String]): Unit = {
//    val person = new Person("K.O", 10)
//    println(person.name + "..." + person.age + "..." + person.school)
//
//    val person2 = new Person("zhangsan", 18, "M")
//    println(person2.name + "..." + person2.age + "..."
//      + person2.school + "..." + person2.gender)

    val student = new Student("K.O", 18, "math")

    println(student.name + "..." + student.school + "..." + student.major)

    println(student.toString)

  }

}

/**
  * 跟在类后面的是主构造器
  * @param name
  * @param age
  */
class Person (val name: String, val age: Int) {

  println("Person constructor enter ...")

  val school = "ustc"

  var gender: String = _

  /**
    * 附属构造器
    */
  def this(name: String, age: Int, gender: String) {
    this(name, age) //附属构造器的第一行代码，必须要调用主构造器或者其他附属构造器
    this.gender = gender
  }

  println("Person constructor leave ...")
}

class Student (name: String, age: Int, val major: String) extends Person (name, age) {

  println("Person Student leave ...")

  override val school: String = "peking"

  override def toString: String = "override def toString"

  println("Person Student leave ...")

}