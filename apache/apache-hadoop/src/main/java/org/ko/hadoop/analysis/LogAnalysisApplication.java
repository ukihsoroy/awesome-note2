package org.ko.hadoop.analysis;

//import com.kumkee.userAgent.UserAgent;
//import com.kumkee.userAgent.UserAgentParser;
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
 * 使用MapReduce来完成：统计浏览器访问次数
 */
public class LogAnalysisApplication {

    private static final Logger _LOGGER = LoggerFactory.getLogger(LogAnalysisApplication.class);

    /**
     * Map: 读取输入的文件
     */
    public static class LogAnalysisMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

        private LongWritable one = new LongWritable(1);

        /**
         * UserAgentParser 用户信息格式化工具
         */
//        private UserAgentParser userAgentParser;

        /**
         * 初始化
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
//            userAgentParser  = new UserAgentParser();
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //1. 接收到的每一个行数据: 就是一行记录信息
            String line = value.toString();

            //2. 解析用户信息
//            UserAgent agent = userAgentParser.parse(line);

            //3. 通过上下文把Map处理结果输出
//            context.write(new Text(agent.getBrowser()), one);
        }

        /**
         * 销毁
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
//            userAgentParser = null;
        }
    }

    /**
     * Reduce: 归并操作
     */
    public static class LogAnalysisReduce extends Reducer<Text, LongWritable, Text, LongWritable> {

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
        Job job = Job.getInstance(configuration, "logAnalysis");

        //3. 设置Job的处理类
        job.setJarByClass(LogAnalysisApplication.class);

        //4. 设置作业处理的输入路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));

        //5. 设置Map相关参数
        job.setMapperClass(LogAnalysisApplication.LogAnalysisMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(LongWritable.class);

        //6. 设置Reduce相关参数
        job.setReducerClass(LogAnalysisApplication.LogAnalysisReduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        //7. 设置作业处理的输出路径
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        //8. 提交
        System.exit(job.waitForCompletion(true) ? 0 : 1);

    }
}
