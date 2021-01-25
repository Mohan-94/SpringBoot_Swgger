package com.app.emp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.app.emp.model.Employee;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer> {


}
