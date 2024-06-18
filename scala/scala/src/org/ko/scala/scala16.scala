package org.ko.scala

/**
  * scala实现快排
  */
object scala16 {

  def quickSort (ary: List[Int]): List[Int] = {
    if (ary.length < 2) ary
    else quickSort(ary.filter(ary.head > _)) ++
      ary.filter(ary.head == _) ++
      quickSort(ary.filter(ary.head < _))
  }

  def main(args: Array[String]): Unit = {
    val ary = quickSort(List(2, 5, 1, 7, 6, 3, 9, 8, 4))
    println(ary)
  }
}
