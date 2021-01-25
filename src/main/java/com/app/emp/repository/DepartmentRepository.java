package com.app.emp.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.emp.model.Department;

public interface DepartmentRepository extends CrudRepository<Department, Integer> {

	Optional<Department> findById(Integer id);

}
