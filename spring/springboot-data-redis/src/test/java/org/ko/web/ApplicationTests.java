package org.ko.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Person> redisTemplate;

    @Test
    public void test() throws Exception {

        // 保存字符串
        stringRedisTemplate.opsForValue().set("name", "K.O");
        Assert.assertEquals("K.O", stringRedisTemplate.opsForValue().get("name"));

        // 保存对象 #注意: Person需要实现Serializable序列化接口
        Person person = new Person();
        person.setId(1L);
        person.setName("K.O");
        person.setAge((short)27);
        person.setBirthday(new Date());
        redisTemplate.opsForValue().set(person.getName(), person);

        person.setId(2L);
        person.setName("Tiger");
        person.setAge((short)28);
        person.setBirthday(new Date());
        redisTemplate.opsForValue().set(person.getName(), person);

        person.setId(3L);
        person.setName("Sultan");
        person.setAge((short)29);
        person.setBirthday(new Date());
        redisTemplate.opsForValue().set(person.getName(), person);

        RedisOperations<String, Person> operations = redisTemplate.opsForValue().getOperations();

        Assert.assertEquals(27, redisTemplate.opsForValue().get("K.O").getAge().intValue());
        Assert.assertEquals(28, redisTemplate.opsForValue().get("Tiger").getAge().intValue());
        Assert.assertEquals(29, redisTemplate.opsForValue().get("Sultan").getAge().intValue());

    }
}
