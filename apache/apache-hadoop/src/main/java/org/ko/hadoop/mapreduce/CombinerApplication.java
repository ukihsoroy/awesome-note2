package org.ko.hadoop.mapreduce;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 使用MapReduce开发WordCount应用程序
 */
public class CombinerApplication {

    private static final Logger _LOGGER = LoggerFactory.getLogger(CombinerApplication.class);

    /**
     * Map: 读取输入的文件
     */
    public static class WordCountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

        private LongWritable one = new LongWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //1. 接收到的每一个行数据
            String line = value.toString();
            if (StringUtils.isNotBlank(line)) {
                //2. 按空格拆分
                String[] words = line.split(" ");
                for (String word : words) {
                    //3. 通过上下文把Map处理结果输出
                    context.write(new Text(word), one);
                }
            }
        }
    }

    /**
     * Reduce: 归并操作
     */
    public static class WordCountReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

        @Override
        protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
            long sum = 0;
            //1. 求key出现的次数总和
            for (LongWritable value : values) {
                sum += value.get();
            }

            //2. 最终统计结果的输出
            context.write(key, new LongWritable(sum));
        }
    }

    /**
     * 定义Driver: 封装了MapReduce作业的所有信息
     * @param args
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1. 创建Configuration
        Configuration configuration = new Configuration();

        //1.1 准备清理已存在的输出目录
        Path outputPath = new Path(args[1]);
        FileSystem fs = FileSystem.get(configuration);
        if (fs.exists(outputPath)) {
            fs.delete(outputPath, true);
            _LOGGER.info("rm -rf output path success.");
        }

        //2. 创建一个Job
        Job job = Job.getInstance(configuration, "wordcount");

        //3. 设置Job的处理类
        job.setJarByClass(CombinerApplication.class);

        //4. 设置作业处理的输入路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));

        //5. 设置Map相关参数
        job.setMapperClass(WordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //6. 设置Reduce相关参数
        job.setReducerClass(WordCountReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //7. 通过Job设置combiner处理类, 其实逻辑上和我们的Reduce是一模一样的
        job.setCombinerClass(WordCountReduce.class);

        //7. 设置作业处理的输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //8. 提交
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }


}
