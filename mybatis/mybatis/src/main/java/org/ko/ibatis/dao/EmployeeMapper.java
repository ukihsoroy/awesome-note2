package org.ko.ibatis.dao;

import org.ko.ibatis.domain.Employee;

import java.util.List;

public interface EmployeeMapper {

    List<Employee> findAll ();

}
