package com.vitalis.manager.dto;

import lombok.Data;

@Data // Esto genera el setData automáticamente si tenés Lombok
public class ApiResponseError {
    private boolean success = false;
    private String message;
    private Object data; // <--- ESTA es la línea que te falta

    public ApiResponseError(String message) {
        this.message = message;
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

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
    
    
}