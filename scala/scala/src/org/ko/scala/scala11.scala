package org.ko.scala

object scala11 {

  def main(args: Array[String]): Unit = {
    val a = List(1, 2, 3)
    println(a)

    //创建新的list， :: 连接操作符
    //元素::集合  将元素添加到集合
    val b = 0 :: a
    println(b)

    val c = "x" :: "y" :: "z" :: Nil
    println(c)

    //::: 连接集合
    val d = a ::: c
    println(d)

    //取出集合数据, head 去除第一个数据
    println(a.head)
    println(b.head)
    println(c.head)
    println(d.head)

    //tail 去除除了第一个的列表
    println(a.tail)
    println(b.tail)
    println(c.tail)
    println(d.tail)

    //判断集合是否为空
    println(a.isEmpty)
    println(Nil.isEmpty)

    val str = mapListToString(a)
    println(str)

  }

  def mapListToString (l: List[Any]): String = {
    if (l.isEmpty)
      ""
    else
      l.head.toString + mapListToString(l.tail)
  }

}
