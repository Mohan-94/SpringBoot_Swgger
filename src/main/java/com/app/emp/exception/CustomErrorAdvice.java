package com.app.emp.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomErrorAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(ApiException.class)
	public final ResponseEntity<Object> handleAllExceptions(ApiException ex, final HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(ex.getStatus(), ex.getMessage(), request.getRequestURL().toString());
		return new ResponseEntity<>(error, ex.getStatus());
	}
}
