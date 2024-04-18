package org.ko.web.repository;

import org.ko.web.domain.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames = "users")
public interface UserJpaRepository extends JpaRepository<User, Integer> {

    @Cacheable(key = "#p0", condition = "#p0.length() < 10")
    User findByName(String name);

}
