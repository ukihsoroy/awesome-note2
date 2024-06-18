package com.imooc;

import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisClientTests {

    private Jedis jedis;

    @Before
    public void setup () {
        jedis = new Jedis("192.168.1.7", 6379);
        jedis.auth("tiger");
    }

    @Test
    public void whenSessionWriteSuccess() {
        String value = jedis.get("h9xg250hp1xilx29c42sgcytq4unxizp");
        System.out.println(value);
    }

}
