package org.ko.scala.course06

import scala.util.Random

object MatchApp extends App {

//  val names = Array("Akiho Yoshizawa", "YuiHatano", "Aoi Sola")
//
//  val name = names(Random.nextInt(names.length))
//
//  name match {
//    case "Akiho Yoshizawa" => println("吉老师")
//    case "YuiHatano" => println("波老师")
//    case _ => println("真不知道你们在说什么....")
//  }
//
//
//  def judgeGrade(grade: String): Unit = {
//    grade match {
//      case "A" => println("Excellent...")
//      case "B" => println("Good...")
//      case "C" => println("Just so so...")
//      case _ => println("You need work harder...")
//    }
//  }
//
//  judgeGrade("A")
//  judgeGrade("B")
//  judgeGrade("D")

  /**
    * 扩展 双重过滤
    */
//  def judgeGrade(name: String, grade: String): Unit = {
//    grade match {
//      case "A" => println("Excellent...")
//      case "B" => println("Good...")
//      case "C" => println("Just so so...")
//      case _ if name == "lisi" => println(name + ", You are a good boy, but...")
//      case _ => println("You need work harder...")
//    }
//  }
//
//  judgeGrade("zhangsan", "D")
//  judgeGrade("lisi", "A")
//  judgeGrade("lisi", "D") //双重过滤

  /**
    * 数组匹配
    */
//  def greeting (array: Array[String]): Unit = {
//    array match {
//      case Array("zhangsan") => println("Hi:ZhangSan")
//      case Array(x,y) => println("Hi:" + x + ", " + y)
//      case Array("zhangsan", _*) => println("Hi:ZhangSan and other friends...")
//      case _ => println("Hi: everybody...")
//    }
//  }
//
//  greeting(Array("zhangsan")) //Hi:ZhangSan
//  greeting(Array("lisi", "wangwu")) //Hi:lisi, wangwu
//  greeting(Array("zhangsan", "lisi", "wangwu")) //Hi:ZhangSan and other friends...
//  greeting(Array("lisi", "zhangsan", "wangwu")) //Hi: everybody...


  /**
    * List匹配
    */
//  def greeting (list: List[String]): Unit = {
//    list match {
//      case "zhangsan"::Nil => println("Hi:ZhangSan")
//      case x::y::Nil => println("Hi:" + x + ", " + y)
//      case "zhangsan"::tail => println("Hi:ZhangSan and other friends...")
//      case _ => println("Hi: everybody...")
//    }
//  }
//
//    greeting(List("zhangsan")) //Hi:ZhangSan
//    greeting(List("lisi", "wangwu")) //Hi:lisi, wangwu
//    greeting(List("zhangsan", "lisi", "wangwu")) //Hi:ZhangSan and other friends...
//    greeting(List("lisi", "zhangsan", "wangwu")) //Hi: everybody...


  /**
    * 类型匹配
    */
//  def matchType(obj: Any): Unit = {
//    obj match {
//      case x: Int => println("Int")
//      case s: String => println("String")
//      case m: Map[_,_] => m.foreach(println)
//      case _ => println("other type.")
//    }
//  }
//
//  matchType(1)
//  matchType("2")
//  matchType(Map("name" -> "K.O"))


  class Person
  case class CTO(name:String, floor:String) extends Person
  case class Employee(name:String, floor:String) extends Person
  case class Other(name:String) extends Person

  def caseClassMatch(person: Person): Unit = {
    person match {
      case CTO(name, floor) => println("CTO name is " + name + " floor is " + floor)
      case Employee(name, floor) => println("Employee name is " + name + " floor is " + floor)
      case _ => println("other")
    }
  }

  caseClassMatch(CTO("K.O", "22"))
  caseClassMatch(Employee("zhangsan", "21"))
  caseClassMatch(Other("lisi"))


}
