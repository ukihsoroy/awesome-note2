package org.ko.mybatis.mapper;

import org.ko.mybatis.entity.Person;
import org.ko.mybatis.entity.PersonExample;
import org.springframework.stereotype.Repository;

/**
 * PersonDAO继承基类
 */
@Repository
public interface PersonDAO extends MyBatisBaseDao<Person, Long, PersonExample> {

}