package org.ko.web.repository;

import org.ko.web.domain.User;
import org.springframework.data.repository.CrudRepository;


public interface UserCrudRepository extends CrudRepository<User, Integer> {

}
