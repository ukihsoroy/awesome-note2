package org.ko.concurrency.example.cache;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

// http://redis.cn/
@Component
public class RedisClient {

    @Autowired
    private JedisPool jedisPool;

    public void set (String key, String value) throws Exception {

        try (Jedis jedis = jedisPool.getResource()) {
            jedis.set(key, value);
        }
    }

    public String get (String key) throws Exception {
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.get(key);
        }
    }


}
