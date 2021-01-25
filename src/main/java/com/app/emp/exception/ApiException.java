package com.app.emp.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;
	private Throwable exception;
	private String apiName;
	private Exception ex;

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public String getApiName() {
		return apiName;
	}

	public void setApiName(String apiName) {
		this.apiName = apiName;
	}

	public Exception getEx() {
		return ex;
	}

	public void setEx(Exception ex) {
		this.ex = ex;
	}

	public ApiException(Throwable cause) {
		super(cause);
	}


	public ApiException(HttpStatus status, String message, String apiName, Exception ex) {
		this.status = status;
		this.message = message;
		this.apiName = apiName;
		this.ex = ex;
	}

	@Override
	public String toString() {
		return "ApiException{" +
				"message='" + message + '\'' +
				", status=" + status +
				", apiName='" + apiName + '\'' +
				", ex=" + ex +
				'}';
	}
}
