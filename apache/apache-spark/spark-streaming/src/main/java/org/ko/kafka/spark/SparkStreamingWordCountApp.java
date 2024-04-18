package org.ko.kafka.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;

import java.util.Arrays;

/**
 * 使用Java开发Spark Streaming应用程序
 */
public class SparkStreamingWordCountApp {

    public static void main(String[] args) throws InterruptedException {
        SparkConf conf = new SparkConf().setMaster("local[2]")
                .setAppName("SparkStreamingWordCountApp");

        JavaStreamingContext jssc = new JavaStreamingContext(conf, Durations.seconds(5));

        //1. 读取
        JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost", 9999);

        //2. 处理数据，拆分-转换
        JavaPairDStream<String, Integer> counts = lines.flatMap(x -> Arrays.asList(x.split(" ")).iterator())
                .mapToPair(word -> new Tuple2<>(word, 1))
                .reduceByKey((x, y) -> x + y);

        //3. 获取结果
        counts.print();

        //4. 开始ssc
        jssc.start();
        jssc.awaitTermination();
    }
}
