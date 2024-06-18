package org.ko.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Kafka 生产者
 * version 2.0
 */
public class KafkaProduct extends Thread{

    private String topic;

    private static Producer<Integer, String> producer;

    public KafkaProduct(String topic) {
        this.topic = topic;
        Properties props = new Properties();
        //broker地址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.BOOTSTRAP_SERVERS);
        //请求时候需要验证
        props.put(ProducerConfig.ACKS_CONFIG, KafkaProperties.ACKS_CONFIG);
        //请求失败时候需要重试
        props.put(ProducerConfig.RETRIES_CONFIG, KafkaProperties.RETRIES_CONFIG);
        //生产者就会尝试将记录组合成一个batch的请求。 这有助于客户端和服务器的性能。不能大于此默认值，否则浪费内存，反而降低吞吐量
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, KafkaProperties.BATCH_SIZE_CONFIG);
        //汇聚一定时间内的记录一起发出
        props.put(ProducerConfig.LINGER_MS_CONFIG, KafkaProperties.LINGER_MS_CONFIG);
        //内存缓存区大小
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, KafkaProperties.BUFFER_MEMORY_CONFIG);
        //指定消息key序列化方式
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
        //指定消息本身的序列化方式
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producer = new KafkaProducer<>(props);
    }

    @Override
    public void run() {
        int messageNo = 1;
        while (true) {
            String message = "message_" + messageNo;
            ProducerRecord<Integer, String> record = new ProducerRecord<>(topic, messageNo, message);
            System.out.println("send message: " + message);
            producer.send(record, (metadata, e) -> System.out.println(metadata.offset()));
            messageNo ++;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
