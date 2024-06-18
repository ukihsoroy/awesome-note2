package org.ko.jwt.system.repository;

import org.ko.jwt.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author shuang.kou
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    @Transactional
    void deleteByUsername( String username);

//    @Query("select status from user where username= :username")
//    Optional<String> findUserStatusByName(@Param("username") String username);
}
