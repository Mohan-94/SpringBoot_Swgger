package com.app.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.emp.exception.ApiException;
import com.app.emp.exception.Constants;
import com.app.emp.exception.SuccessResponse;
import com.app.emp.model.Department;
import com.app.emp.service.DepartmentService;

@RestController
public class DepartmentController {
	@Autowired
	private DepartmentService departmentService;

	@GetMapping("/departments")
	public ResponseEntity<Object> getAllDepartment() throws ApiException {
		List<Department> depList = null;
		try {
			depList = departmentService.getAllDepartments();
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
						Constants.POSTGRESQL, e);
			}
		}
		return ResponseEntity.ok(depList);
	}

	@GetMapping("/department/{id}")
	public ResponseEntity<Object> getDepartmentyId(@PathVariable("id") final Integer id) throws ApiException {
		Department Department = null;
		try {
			Department = departmentService.getDepartment(id);
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
						Constants.POSTGRESQL, e);
			}
		}
		return ResponseEntity.ok(Department);
	}

	@PostMapping("/department")
	public ResponseEntity<Object> createDepartment(@RequestBody Department department) throws ApiException {
		try {
			departmentService.createDepartment(department);
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
						Constants.POSTGRESQL, e);
			}
		}
		return ResponseEntity.ok(department);
	}

	@PutMapping("/department/{id}")
	public ResponseEntity<Department> updateDepartment(@PathVariable("id") final Integer id,
			@RequestBody final Department department) throws ApiException {
		try {
			department.setId(id);
			departmentService.updateDepartment(department);
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
						Constants.POSTGRESQL, e);
			}

		}
		return new ResponseEntity<Department>(department, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/department/{id}")
	public ResponseEntity<Object> deleteRole(@PathVariable("id") Integer id) throws ApiException {
		try {
			departmentService.deleteDepartmentById(id);
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL,
						Constants.POSTGRESQL, e);
			}
		}
		return new ResponseEntity<>(new SuccessResponse("Role has been deleted"), HttpStatus.ACCEPTED);
	}
}
