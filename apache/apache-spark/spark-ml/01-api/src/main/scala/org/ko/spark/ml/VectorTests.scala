package org.ko.spark.ml

import org.apache.spark.mllib.linalg.{Vectors, Vector}

/**
 * description: VectorTests <br>
 * date: 2020/3/20 23:33 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
object VectorTests {

  def main(args: Array[String]): Unit = {
    val v1 = Vectors.dense(1, 2, 3, 4)
    println(v1)

    val v2 = breeze.linalg.DenseVector(1, 2, 3, 4)

    println(v2)
    println(v2 + v2)
    println(v2 * v2.t)

  }

}
