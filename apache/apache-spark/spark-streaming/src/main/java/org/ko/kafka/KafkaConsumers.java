package org.ko.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * kafka version #2.0
 */
public class KafkaConsumers extends Thread {

    private String topic;

    public KafkaConsumers(String topic) {
        this.topic = topic;
    }

    private KafkaConsumer<Integer, String> createConsumer () {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaProperties.BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG ,"test") ;
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return  new KafkaConsumer<>(props);
    }

    @Override
    public void run() {
//        KafkaConsumer<Integer, String> consumer = createConsumer();
//        consumer.subscribe(Collections.singletonList(topic));
//        while (true) {
//            ConsumerRecords<Integer, String> records = consumer.poll(Duration.ofSeconds(1));
//            for (ConsumerRecord<Integer, String> record : records) {
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//            }
//        }
    }
}
