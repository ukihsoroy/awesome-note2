package org.ko.web.repository;

import org.ko.web.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserPagingSortRepository extends PagingAndSortingRepository<User, Integer> {


}
