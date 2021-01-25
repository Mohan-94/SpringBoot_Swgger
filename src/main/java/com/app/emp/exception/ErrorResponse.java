package com.app.emp.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import org.springframework.http.HttpStatus;

@JsonInclude(Include.NON_NULL)
class ErrorResponse {

	private HttpStatus status;
	private String message;
	private String field;
	private String url;

	ErrorResponse(HttpStatus status) {
		this.status = status;
	}

	ErrorResponse(HttpStatus status, String message, String url) {
		this.status = status;
		this.message = message;
		this.url = url;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
