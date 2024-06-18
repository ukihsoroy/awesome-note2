package org.ko.web.conf;

import org.ko.web.domain.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis默认是守护进程的-只能在本地使用localhost访问
 * 开启IP访问需要配置
 */
@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Person> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Person> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }


}
