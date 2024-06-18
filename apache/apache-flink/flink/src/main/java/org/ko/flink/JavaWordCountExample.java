package org.ko.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @author zhiyuan.shen
 */
public class JavaWordCountExample {

    public static void main(String[] args) throws Exception {
        //构建环境
        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //通过字符串构建数据集
        DataSet<String> text = env.fromElements(
                "i love three things in the world",
                "the sun",
                "the moon",
                "and you"
        );

        //分割字符串、按照key进行分组、统计相同的key个数
        DataSet<Tuple2<String, Integer>> wordCounts = text
                .flatMap(new LineSplitter())
                .groupBy(0)
                .sum(1);

        //打印
        wordCounts.print();
    }

    public static class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {

        @Override
        public void flatMap(String line, Collector<Tuple2<String, Integer>> out) throws Exception {
            for (String word : line.split(" ")) {
                out.collect(new Tuple2<>(word, 1));
            }
        }
    }
}
