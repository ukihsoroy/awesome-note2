package org.ko.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.dao.domain.Person;
import org.ko.web.dao.example.PersonExample;
import org.ko.web.dao.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    @Autowired
    public PersonMapper personMapper;

    @Test
    public void testJDBC () {
        //插入1条数据
        Person person = new Person();
        person.setName("Tiger");
        person.setAge((short)21);
        person.setBirthday(new Date());
        personMapper.insert(person);

        //查询应该有1条数据
        Assert.assertEquals(personMapper.countByExample(null),1L);
    }

    @Test
    public void testFindOne () {
        //查询一条
        Person person = personMapper.selectByPrimaryKey(12L);
        //ID不应该为空
        Assert.assertNotNull(person.getId());
    }
    @Test
    public void testFindAll () {
        //再插入一条数据
        Person person = new Person();
        person.setName("Jim");
        person.setAge((short)22);
        person.setBirthday(new Date());
        personMapper.insert(person);
        //查询全部
        List<Person> people = personMapper.selectByExample(null);
        //应该有2条数据
        Assert.assertEquals(people.size(), 2);
    }

    @Test
    public void testExample () {
        //再插入一条数据
        Person person = new Person();
        person.setName("K.O");
        person.setAge((short)23);
        person.setBirthday(new Date());
        personMapper.insert(person);

        //查询年龄大于22岁的
        PersonExample pe = new PersonExample();
        pe.createCriteria()
                .andAgeGreaterThanOrEqualTo((short)22);
        List<Person> people = personMapper.selectByExample(pe);

        //应该有2条数据
        Assert.assertEquals(people.size(), 2);
    }

    @Test
    public void testUpdate () {
        //将ID为2的name改为jim-只修改有效信息-忽略为null的字段
        Person person = new Person();
        person.setId(12L);
        person.setName("Ben");
        int i = personMapper.updateByPrimaryKeySelective(person);
        //返回应该为1
        Assert.assertEquals(i, 1);

        //查询ID为2的人
        Person result = personMapper.selectByPrimaryKey(12L);
        //name应该为jim
        Assert.assertEquals(result.getName(), "Ben");
    }

    @Test
    public void testDeleteById () {
        //删除ID为2的人
        int i = personMapper.deleteByPrimaryKey(12L);
        //返回应该为1
        Assert.assertEquals(i, 1);

        //查询全部
        List<Person> people = personMapper.selectByExample(null);
        //应该有4条数据
        Assert.assertEquals(people.size(), 2);
    }

    @Test
    public void testDeleteAll () {
        //通过Example删除数据-这里传null-全部删除
        //可以通过Example配置删除Sql
        int i = personMapper.deleteByExample(null);
        //一共4条, 返回应该为4
        Assert.assertEquals(i, 2);

        //查询全部
        List<Person> people = personMapper.selectByExample(null);
        //应该为空
        Assert.assertEquals(people.size(), 0);
    }

}
