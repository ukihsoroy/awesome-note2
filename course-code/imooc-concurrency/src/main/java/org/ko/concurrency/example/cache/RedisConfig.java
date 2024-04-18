package org.ko.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.JedisPool;

@Configurable
public class RedisConfig {

    @Bean
    public JedisPool jedisPool (@Value("${jedis.host}") String host,
                                @Value("${jedis.port}") int port) {
        return new JedisPool(host, port);
    }
}
