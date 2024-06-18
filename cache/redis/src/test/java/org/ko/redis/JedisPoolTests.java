package org.ko.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ko.redis.pool.RedisExecutor;
import redis.clients.jedis.Jedis;

public class JedisPoolTests {

    private Jedis jedis;

    @BeforeEach void setup() {
        jedis = RedisExecutor.getJedis();
    }

    @Test void testPool() {
        jedis.set("name", "K.O");
        System.out.println(jedis.get("name"));
    }
}
