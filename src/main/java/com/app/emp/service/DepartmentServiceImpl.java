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
import com.app.emp.model.Department;
import com.app.emp.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	@Autowired
	private DepartmentRepository departmentRepo;

	@Override
	public List<Department> getAllDepartments() throws ApiException {
		List<Department> depList = null;
		try {
			depList = (List<Department>) departmentRepo.findAll();
		} catch (DataAccessException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, e);
		}
		return depList;
	}

	@Override
	public Department getDepartment(Integer id) throws ApiException {
		Department department = null;
		try {
			department = departmentRepo.findById(id).get();
		} catch (DataAccessException e) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, e);
		}
		return department;
	}

	@Override
	@Transactional
	public Department createDepartment(Department department) throws ApiException {
		try {
			return departmentRepo.save(department);
		} catch (DataIntegrityViolationException ex) {
			throw new ApiException(HttpStatus.CONFLICT, Constants.DUPLICATE_INSERT, Constants.POSTGRESQL, ex);
		} catch (DataAccessException ex) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, ex);
		}
	}

	@Override
	@Transactional
	public Department updateDepartment(Department department) throws ApiException {
		try {
			Department dept = new Department();
			dept.setId(department.getId());
			dept.setDeptName(department.getDeptName());
			return departmentRepo.save(dept);
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}

	}

	@Override
	@Transactional
	public void deleteDepartmentById(Integer id) throws ApiException {
		try {
			departmentRepo.deleteById(id);
		} catch (DataAccessException dataAccessException) {
			throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
					Constants.POSTGRESQL, dataAccessException);
		}
	}

}
