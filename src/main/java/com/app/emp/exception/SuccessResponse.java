package com.app.emp.exception;

public class SuccessResponse {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SuccessResponse(String message) {
        this.message = message;
    }
}
