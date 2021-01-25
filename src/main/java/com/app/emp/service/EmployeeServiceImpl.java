package com.app.emp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.emp.exception.ApiException;
import com.app.emp.exception.Constants;
import com.app.emp.model.Employee;
import com.app.emp.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;

	@Override
	public List<Employee> getAllEmployees() throws ApiException {
		List<Employee> empList = null;
		try {

			empList = (List<Employee>) employeeRepo.findAll();
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}
		return empList;
	}

	@Override
	public Employee getEmployee(Integer id) throws ApiException {
		Employee emp = null;
		try {
			emp = employeeRepo.findById(id).get();
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}
		return emp;

	}

	@Override
	@Transactional
	public Employee createEmployee(Employee employee) throws ApiException {
		try {
			return employeeRepo.save(employee);
		} catch (DataIntegrityViolationException dataIntegrityViolationException) {
			throw new ApiException(HttpStatus.CONFLICT, Constants.DUPLICATE_INSERT, Constants.POSTGRESQL,
					dataIntegrityViolationException);
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}
	}

	@Override
	@Transactional
	public Employee updateEmployee(Employee employee) throws ApiException {
		try {
			Employee employeeUpdate = new Employee();

			employeeUpdate.setId(employee.getId());
			employeeUpdate.setEmailId(employee.getEmailId());
			employeeUpdate.setEmpId(employee.getEmpId());
			employeeUpdate.setFirstName(employee.getFirstName());
			employeeUpdate.setLastName(employee.getLastName());
			employeeUpdate.setMobileNo(employee.getMobileNo());
			return employeeRepo.save(employeeUpdate);
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}

	}

	@Override
	@Transactional
	public void deleteEmployeeById(Integer id) throws ApiException {
		try {
			employeeRepo.deleteById(id);
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}

	}

	@Transactional
	@Override
	public void deleteAllEmployees() throws ApiException {
		try {
			employeeRepo.deleteAll();
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}

	}

}
