package org.ko.web.repository;


import org.ko.web.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @see Repository CTRL + T
 * <T, ID> 泛型
 * T是你要操作的对象
 * ID是一个主键
 * 使用RepositoryDefinition注解
 * 可以不继承Repository核心接口
 */
@RepositoryDefinition(domainClass = User.class, idClass = Integer.class)
public interface UserRepository {   //extends Repository<User, Integer>

    User findByName(String name);

    //where name like ?% and age < ?
    List<User> findByNameStartingWithAndAgeLessThan(String name, Integer age);

    //where name in (?, ?, ...) or age < ?
    List<User> findByNameInOrAgeLessThan(List<String> names, Integer age);

    @Query("SELECT u FROM User u WHERE id = (SELECT MAX(id) FROM User)")
    User getUserByMaxId();

    @Query("SELECT u FROM User u WHERE u.name = ?1 AND u.age = ?2")
    List<User> queryParams(String name, Integer age);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.age = :age")
    List<User> queryParamsName(@Param("name") String name, @Param("age") Integer age);

    @Query("SELECT u FROM User u WHERE u.name LIKE %?1")
    List<User> queryLikeName(String name);

    @Query(nativeQuery = true, value = "SELECT count(1) FROM User")
    long getCount();

    @Modifying
    @Query("UPDATE User u SET u.name = :name where u.id = :id")
    int updateById(@Param("id") Integer id, @Param("name") String name);
}
