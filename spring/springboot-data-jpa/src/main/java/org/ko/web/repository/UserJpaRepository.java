package org.ko.web.repository;

import org.ko.web.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserJpaRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

}
