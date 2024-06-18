package org.ko.web.service;

import org.ko.web.domain.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class PersonService {

    @Autowired JdbcTemplate jdbcTemplate;

    /**
     * 通过ID查找当前对象
     * @param id
     * @return
     */
    public Person findById (Long id) {
        Person person = new Person();
        jdbcTemplate.query("SELECT * FROM person p WHERE p.id = ?", (ResultSet resultSet) -> buildPerson(resultSet, person), id);
        return person;
    }

    /**
     * 查询全部
     * @return
     */
    public List<Person> findAll () {
        return jdbcTemplate.query("SELECT * FROM person", (ResultSet resultSet, int i) -> {
            Person person = new Person();
            buildPerson(resultSet, person);
            return person;
        });
    }

    /**
     * 插入数据
     * @param name
     * @param age
     * @param birthday
     * @return
     */
    public int insert (String name, Short age, Date birthday) {
        return jdbcTemplate.update("INSERT INTO person(name, age, birthday) VALUES (?, ?, ?)", name, age, birthday);
    }

    /**
     * 通过ID更新name
     * @param id
     * @param name
     * @return
     */
    public int updateById (Long id, String name) {
        return jdbcTemplate.update("UPDATE person p SET p.name = ? WHERE p.id = ?", name, id);
    }

    /**
     * 通过ID删除一条数据
     * @param id
     * @return
     */
    public int deleteById (Long id) {
        return jdbcTemplate.update("DELETE p FROM person p WHERE p.id = ?", id);
    }

    /**
     * 删除全部
     * @return
     */
    public int deleteAll () {
        return jdbcTemplate.update("DELETE p FROM person p");
    }

    /**
     * 查看条数
     * @return
     */
    public Integer count () {
        return jdbcTemplate.queryForObject("SELECT COUNT(1) FROM person", Integer.class);
    }

    /**
     * 构建Person对象
     * @param resultSet
     * @param person
     * @throws SQLException
     */
    protected void buildPerson (ResultSet resultSet, Person person) throws SQLException {
        person.setId(resultSet.getLong(1));
        person.setName(resultSet.getString(2));
        person.setAge(resultSet.getShort(3));
        person.setBirthday(resultSet.getDate(4));
    }
}
