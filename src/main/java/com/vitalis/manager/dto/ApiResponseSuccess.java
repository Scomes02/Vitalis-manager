package com.vitalis.manager.dto;

import lombok.Data;

@Data // Genera getters/setters automáticos (aunque abajo los escribiste manual, lo cual es redundante pero válido).
public class ApiResponseSuccess<T> {
    
    // Por defecto es true. Indica al Frontend que la operación funcionó.
    private boolean success = true; 
    
    // Mensaje amigable (ej: "Médico creado con éxito").
    private String message;
    
    // El contenido real. La 'T' se reemplaza por el tipo de dato que uses en el Controller.
    private T data;

    // Constructor para facilitar la creación en una sola línea en el Controller.
    public ApiResponseSuccess(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // --- GETTERS Y SETTERS ---
    // (Nota: Como tenés @Data de Lombok arriba, podrías borrar todo esto y funcionaría igual.
    // Pero dejarlos escritos explícitamente no hace daño).
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }
}