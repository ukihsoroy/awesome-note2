package org.ko.web;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ko.web.domain.Person;
import org.ko.web.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTests {

    @Autowired
    private PersonRepository personRepository;

    @Before
    public void setUp() {
        personRepository.deleteAll();
    }

    @Test
    public void test() throws Exception {

        // 创建三个Person，并验证Person总数
        personRepository.save(new Person(1L, "didi", (short)30));
        personRepository.save(new Person(2L, "mama", (short)40));
        personRepository.save(new Person(3L, "kaka", (short)50));
        Assert.assertEquals(3, personRepository.findAll().size());

        // 删除一个Person，再验证Person总数
        Person u = personRepository.findOne(1);
        personRepository.delete(u);
        Assert.assertEquals(2, personRepository.findAll().size());

        // 删除一个Person，再验证Person总数
        u = personRepository.findByName("mama");
        personRepository.delete(u);
        Assert.assertEquals(1, personRepository.findAll().size());

    }
}
