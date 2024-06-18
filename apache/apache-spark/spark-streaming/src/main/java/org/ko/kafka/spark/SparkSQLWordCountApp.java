package org.ko.kafka.spark;

import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.SparkSession;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;

/**
 * 使用Java开发Spark应用程序
 */
public class SparkSQLWordCountApp {

    public static void main(String[] args) {
        SparkSession spark = SparkSession.builder()
                .appName("SparkSQLWordCountApp")
                .master("local[2]")
                .getOrCreate();

        //TODO...处理我们的业务逻辑

        //1. 读取文件，转成Java RDD
        JavaRDD<String> lines = spark.read().textFile("hello.txt").javaRDD();

        //2. 拆分单词
        JavaRDD<String> words = lines.flatMap(x -> Arrays.asList(x.split(" ")).iterator());

        //3. 为每个单词赋值1
        JavaPairRDD<String, Integer> counts = words.mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((x, y) -> x + y);

        //4. 获取结果
        List<Tuple2<String, Integer>> output = counts.collect();

        //5. 输出
        output.forEach(x -> System.out.println(x._1 + ": " + x._2));

        spark.stop();
    }
}
