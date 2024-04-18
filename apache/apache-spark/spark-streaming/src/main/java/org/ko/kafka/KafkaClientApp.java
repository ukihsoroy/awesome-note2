package org.ko.kafka;

/**
 * Kafka API 测试
 */
public class KafkaClientApp {

    public static void main(String[] args) {
        new KafkaProduct(KafkaProperties.TOPIC).start();
        new KafkaConsumers(KafkaProperties.TOPIC).start();
    }
}
