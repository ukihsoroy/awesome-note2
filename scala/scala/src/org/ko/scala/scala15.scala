package org.ko.scala

/**
  * Map
  */
object scala15 {

  def main(args: Array[String]): Unit = {
    //1. 创建
    val p = Map(1 -> "K.O", 2 -> "kayo")
    println(p)

    //2. 取值
    val a = p(1)
    println(a)
    val b = p(2)
    println(b)

    //3. 是否包含
    val c = p.contains(1)
    println(c)

    //4. 获取全部key
    val d = p.keys
    println(d)

    //5. 获取全部value
    val e = p.values
    println(e)

    //6. 添加
    val f = p + (3 -> "Sultan")
    println(f)

    //7. 取最后一个
    val g = p - 1
    println(g)

    //8. 添加多个
    val h = p ++ List(3 -> "Sun", 4 -> "Moon")
    println(h)

    //9. 减去多个
    val i = h -- List(3 , 4)
    println(i)

  }

}
