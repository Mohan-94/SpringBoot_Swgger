package com.app.emp.service;

import java.util.List;

import com.app.emp.exception.ApiException;
import com.app.emp.model.Department;

public interface DepartmentService {
	List<Department> getAllDepartments() throws ApiException;

	Department getDepartment(Integer id) throws ApiException;

	Department createDepartment(Department department) throws ApiException;

	Department updateDepartment(Department department) throws ApiException;

	void deleteDepartmentById(Integer id) throws ApiException;
}
