package org.ko.kafka;

/**
 * Kafka 常量
 */
final class KafkaProperties {

    /**
     * 访问的队列
     */
    static final String TOPIC = "kafkaTest";

    /**
     * kafka 集群地址
     */
    static final String BOOTSTRAP_SERVERS = "192.168.37.128:9092";

    /**
     * 请求失败时候需要重试
     */
    static final Integer RETRIES_CONFIG = 0;

    /**
     * ##请求时是否需要验证
     * 0: 不等待返回结果
     * 1: leader成功收到数据确认后发送下一条
     * -1: 接收到数据才算完成
     */
    static final String ACKS_CONFIG = "1";

    /**
     * 生产者就会尝试将记录组合成一个batch的请求。 这有助于客户端和服务器的性能。不能大于此默认值，否则浪费内存，反而降低吞吐量
     */
    static final Integer BATCH_SIZE_CONFIG = 16384;

    /**
     * 汇聚一定时间内的记录一起发出
     */
    static final Integer LINGER_MS_CONFIG = 1;

    /**
     * 内存缓存区大小
     */
    static final Integer BUFFER_MEMORY_CONFIG = 33554432;

}
