package com.vitalis.manager.dto;

import lombok.Data;

@Data // Gracias a esto (o a los métodos manuales de abajo), el ExceptionHandler puede llamar a .setData()
public class ApiResponseError {
    
    // Por defecto false. Indica al Frontend que hubo un problema.
    private boolean success = false;
    
    // Mensaje del error (ej: "Médico no encontrado").
    private String message;
    
    // Aquí guardamos detalles extra, como la lista de validaciones fallidas.
    // Es 'Object' para ser flexible.
    private Object data; 

    // Constructor simple para cuando solo queremos mandar mensaje (ej: error 500).
    public ApiResponseError(String message) {
        this.message = message;
    }

    // --- GETTERS Y SETTERS ---
    // Igual que antes: son redundantes si tenés @Data, pero necesarios si no usás Lombok.
    // ESTE método 'setData' es el que tu ExceptionHandler estaba buscando desesperadamente antes.
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }
}