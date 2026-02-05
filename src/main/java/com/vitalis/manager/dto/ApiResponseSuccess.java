package com.vitalis.manager.dto;

import lombok.Data;

@Data
public class ApiResponseSuccess<T> {
    private boolean success = true; // Siempre true
    private String message;
    private T data;

    public ApiResponseSuccess(String message, T data) {
        this.message = message;
        this.data = data;
    }

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
}