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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.emp.exception.ApiException;
import com.app.emp.exception.Constants;
import com.app.emp.exception.SuccessResponse;
import com.app.emp.model.Employee;
import com.app.emp.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value="Employee Management System", description="Operations pertaining to employee in Employee Management System")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
    
	@GetMapping("/employees")
	@ApiOperation(value = "View a list of available employees")
	 @ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully retrieved list"),
		        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
		    })
	public ResponseEntity<List<Employee>> getAllEmployees() throws ApiException  {
		List<Employee> eList =null;
		try {
			eList=employeeService.getAllEmployees();
		} catch (ApiException apiEx) {
			throw apiEx;
			
		}
		return new ResponseEntity<List<Employee>>(eList, HttpStatus.OK);
	}
    
	@GetMapping("/employee/{id}")
	@ApiOperation(value = "Get a specific record from database by Id")
	public ResponseEntity<Employee>  getEmployee(@PathVariable Integer id)throws ApiException  {
		Employee employee=null;
		try {
       employee=employeeService.getEmployee(id);
		
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL, Constants.POSTGRESQL, e);
		}
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		

	}

	@PostMapping("/employee")
	@ApiOperation(value = "Add an employee")
	public ResponseEntity<Employee> createEmployee(@ApiParam(value = "Employee object store in database table", required = true)@RequestBody final Employee employee) throws ApiException  {
		try {
		employeeService.createEmployee(employee);
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL, Constants.POSTGRESQL, e);
			}
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);

	}

	@PutMapping("/employee/{id}")
	@ApiOperation(value = "Modify an employee by Id")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") final Integer id,
			@RequestBody final Employee employee) throws ApiException {
		try {
		employee.setId(id);
		employeeService.updateEmployee(employee);
		
		
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL, Constants.POSTGRESQL, e);
			}
		}
		return new ResponseEntity<Employee>(employee, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/employee/{id}")
	@ApiOperation(value = "Delete Employee by Id.")
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable("id") Integer id)throws ApiException{
		try {
			
		employeeService.deleteEmployeeById(id);
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL, Constants.POSTGRESQL, e);
			}
		}
		return new ResponseEntity<>(new SuccessResponse("Employee has been deleted"),HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/employee")
	@ApiOperation(value = "Delete all employees.")
	public ResponseEntity<Object> deleteAllEmployees()throws ApiException  {
		try {
		employeeService.deleteAllEmployees();
		} catch (ApiException apiEx) {
			throw apiEx;
		} catch (Exception e) {
			if (e instanceof TransactionException) {
				throw new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Constants.ERROR_CONNECTING_POSTGRESQL, Constants.POSTGRESQL, e);
			}
		}
		return new ResponseEntity<>(new SuccessResponse("All Employees deleted"),HttpStatus.ACCEPTED);

	}

}
