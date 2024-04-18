package org.ko.mybatis.service;

import org.ko.mybatis.entity.Person;
import org.ko.mybatis.entity.PersonExample;
import org.ko.mybatis.exception.GlobalException;
import org.ko.mybatis.mapper.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PersonService {

    @Autowired private PersonDAO personDAO;

    public Integer insertPerson(Person person) {
        //基础
        return personDAO.insert(person);
    }


    public List<Person> queryPersonByCondition(Short age) {
        //创建example
        PersonExample pe = new PersonExample();

        //拼接sql
        pe.createCriteria().andAgeGreaterThanOrEqualTo(age);

        //通过拼接sql查询数据
        List<Person> people = personDAO.selectByExample(pe);

        //全局异常处理
        if (people.size() == 0) {
            throw new GlobalException("501", "没有年纪这么大的人类。");
        }

        //返回
        return people;
    }

}
