# Spark Streaming imooc 实战课程练习代码

1. 课程地址：[https://coding.imooc.com/class/153.html](https://coding.imooc.com/class/153.html)

## index

```lua
src/main/java
|--KafkaProduct.java kafka生产者
|--KafkaConsumers.java kafka消费者
|--KafkaProperties.java kafka参数类
!--KafkaClientApp.java kafka启动类

src/main/scala
|--NetworkWordCount.scala Spark Streaming 处理socket数据
|--FileWordCount.scala 使用Spark Streaming 处理文件系统(local/HDFS)的数据
|--StatefulWordCount.scala 使用Spark Streaming完成有状态统计
|--ForeachRDDApp.scala 使用Spark Streaming完成有状态统计，并存到mysql中
|--TransformApp.scala 黑名单过滤
|--SqlNetworkWordCount.scala Spark Streaming 和 Spark SQL整合使用


```