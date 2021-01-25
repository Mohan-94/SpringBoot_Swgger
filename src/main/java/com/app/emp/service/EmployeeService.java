package com.app.emp.service;

import java.util.List;

import com.app.emp.exception.ApiException;
import com.app.emp.model.Employee;

public interface EmployeeService {

	List<Employee> getAllEmployees() throws ApiException;

	Employee getEmployee(Integer id) throws ApiException;

	Employee createEmployee(Employee employee) throws ApiException;

	Employee updateEmployee(Employee employee) throws ApiException;

	void deleteEmployeeById(Integer id) throws ApiException;

	void deleteAllEmployees() throws ApiException;

}
