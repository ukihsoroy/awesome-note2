package org.ko.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.dao.PersonMapper;
import org.ko.web.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    @Autowired
    public PersonMapper personMapper;

    @Test
    public void testJDBC () {
        //插入5条数据
        personMapper.insert("Tiger", (short)21, new Date());
        personMapper.insert("Artist", (short)22, new Date());
        personMapper.insert("Sultan", (short)23, new Date());
        personMapper.insert("Ben", (short)24, new Date());
        personMapper.insert("K.O", (short)25, new Date());

        //查询应该有5条数据
        Assert.assertEquals(personMapper.count(),Integer.valueOf(5));
    }

    @Test
    public void testFindOne () {
        //查询一条
        Person person = personMapper.findById(2L);
        //ID不应该为空
        Assert.assertNotNull(person.getId());
    }

    @Test
    public void testFindAll () {
        //查询全部
        List<Person> people = personMapper.findAll();
        //应该有5条数据
        Assert.assertEquals(people.size(), 5);

        if (!CollectionUtils.isEmpty(people)) {
            people.forEach(person -> Assert.assertEquals(person.getId(), null));
        }
    }

    @Test
    public void testUpdate () {
        //将ID为2的name改为jim
        int i = personMapper.updateById(2L, "Jim");
        //返回应该为1
        Assert.assertEquals(i, 1);

        //查询ID为2的人
        Person person = personMapper.findById(2L);
        //name应该为jim
        Assert.assertEquals(person.getName(), "Jim");
    }

    @Test
    public void testDeleteById () {
        //删除ID为2的人
        int i = personMapper.deleteById(2L);
        //返回应该为1
        Assert.assertEquals(i, 1);

        //查询全部
        List<Person> people = personMapper.findAll();
        //应该有4条数据
        Assert.assertEquals(people.size(), 4);
    }

    @Test
    public void testDeleteAll () {
        //删除全部数据
        int i = personMapper.deleteAll();
        //一共4条, 返回应该为4
        Assert.assertEquals(i, 4);

        //查询全部
        List<Person> people = personMapper.findAll();
        //应该为空
        Assert.assertEquals(people.size(), 0);
    }

}
