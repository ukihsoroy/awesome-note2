package org.ko.web.dao;

import org.apache.ibatis.annotations.*;
import org.ko.web.domain.Person;

import java.util.Date;
import java.util.List;

@Mapper
public interface PersonMapper {

    /**
     * 通过ID查找当前对象
     * @param id
     * @return
     */
    @Select("SELECT * FROM person p WHERE p.id = #{id}")
    Person findById (@Param("id") Long id) ;

    /**
     * 查询全部
     * @Results 限制返回结果
     * @return
     */
    @Results({
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "birthday", column = "birthday")
    })
    @Select("SELECT p.name, p.age, p.birthday FROM person p")
    List<Person> findAll ();

    /**
     * 插入数据
     * @param name
     * @param age
     * @param birthday
     * @return
     */
    @Insert("INSERT INTO person(name, age, birthday) VALUES (#{name}, #{age}, #{birthday})")
    int insert (@Param("name") String name, @Param("age") Short age, @Param("birthday") Date birthday) ;

    /**
     * 通过ID更新name
     * @param id
     * @param name
     * @return
     */
    @Update("UPDATE person p SET p.name = #{name} WHERE p.id = #{id}")
    int updateById (@Param("id") Long id, @Param("name") String name) ;

    /**
     * 通过ID删除一条数据
     * @param id
     * @return
     */
    @Delete("DELETE p FROM person p WHERE p.id = #{id}")
    int deleteById (@Param("id") Long id);

    /**
     * 删除全部
     * @return
     */
    @Delete("DELETE p FROM person p")
    int deleteAll ();

    /**
     * 查看条数
     * @return
     */
    @Select("SELECT COUNT(1) FROM person")
    Integer count ();
}
