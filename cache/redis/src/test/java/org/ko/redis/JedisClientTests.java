package org.ko.redis;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JedisClientTests {

    private Jedis jedis;

    @BeforeEach
    void setup () {
        jedis = new Jedis("106.12.110.82", 6379);
        jedis.auth("tiger");
    }

    /**
     * 测试字符串
     */
    @Test void testString () {
        //添加字符串
        jedis.set("name", "K");
        System.out.println(jedis.get("name"));

        //拼接
        jedis.append("name", ".O");
        System.out.println(jedis.get("name"));

        //删除
        jedis.del("name");
        System.out.println(jedis.get("name"));

        //设置多个键值对
        jedis.mset("name", "K.O", "age", "18", "email", "ko.shen@hotmail.com");

        //对某个key进行 +1 操作
        jedis.incr("age");

        var name = jedis.get("name");
        var age = jedis.get("age");
        var email = jedis.get("email");

        System.out.println("name: " + name);
        System.out.println("age: " + age);
        System.out.println("email: " + email);

        jedis.del("name");
        jedis.del("age");
        jedis.del("email");

        //设置过期时间
        jedis.setex("name", 1, "K.O");
        System.out.println("setex name: " + jedis.get("name"));

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("setex name: " + jedis.get("name"));
    }

    /**
     * 测试map
     */
    @Test void testMap () {
        Map<String, String> map = new HashMap<>();
        map.put("name", "K.O");
        map.put("age", "18");
        map.put("email", "ko.shen@hotmail.com");
        jedis.hmset("user", map);
        //会根据key 返回对应的值list
        var rsmap = jedis.hmget("user", "name", "age", "email");
        rsmap.forEach(System.out::println);

        //删除map里面的值
        jedis.hdel("user", "age");
        //因为删除了，所有是null
        System.out.println(jedis.hmget("user", "age"));
        //删掉了age，所有还剩2个
        System.out.println(jedis.hlen("user"));
        System.out.println(jedis.exists("user"));
        System.out.println(jedis.hkeys("user"));
        System.out.println(jedis.hvals("user"));

        jedis.hkeys("user").forEach(key -> System.out.println(jedis.hmget("user", key)));
    }

    /**
     * jedis操作list
     */
    @Test void testList() {
        //打印全部列表，-1代表最后
        System.out.println(jedis.lrange("names", 0, -1));

        //添加元素，从左添加
        jedis.lpush("names", "K.O");
        jedis.lpush("names", "sigma");
        jedis.lpush("names", "alpha");
        jedis.lpush("names", "zhiyuan");

        //再取出所有数据jedis.lrange是按范围取出，
        //第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有
        System.out.println(jedis.lrange("names",0,-1));
        System.out.println(jedis.llen("names"));

        //删除
        jedis.del("names");

        jedis.rpush("names", "K.O");
        jedis.rpush("names", "alpha");
        jedis.rpush("names", "beta");
        System.out.println(jedis.lrange("names",0,-1));
    }

    /**
     * jedis操作set
     */
    @Test void testSet() {
        //添加元素
        jedis.sadd("students", "K.O");
        jedis.sadd("students", "alpha");
        jedis.sadd("students", "beta");

        //移除
        jedis.srem("students", "beta");
        //获取所有加入的value
        System.out.println(jedis.smembers("students"));
        //判断students中是否有叫beta的元素
        System.out.println(jedis.sismember("students", "beta"));
        //返回集合中的一个随机元素
        System.out.println(jedis.srandmember("students"));
        //返回结合的元素个数
        System.out.println(jedis.scard("students"));
    }

    @Test void testSort() {
        //jedis排序
        //注意，此处的rpush和lpush是List的操作。是一个双向链表（但从表现来看的）
        jedis.rpush("num", "1");
        jedis.lpush("num", "5");
        jedis.rpush("num", "3");
        jedis.rpush("num", "2");
        //[5, 1, 3, 2]
        System.out.println(jedis.lrange("num", 0, -1));
        //排序后输出，[1, 2, 3, 5]
        System.out.println(jedis.sort("num"));
        //但是里面的结果并没有改变，[5, 1, 3, 2]
        System.out.println(jedis.lrange("num", 0, -1));
    }

    @Test void test1() {
        String value = jedis.get("h9xg250hp1xilx29c42sgcytq4unxizp");
        System.out.println(value);
    }

}
