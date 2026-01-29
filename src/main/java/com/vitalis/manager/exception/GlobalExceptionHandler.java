package com.vitalis.manager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vitalis.manager.dto.ApiResponseError; // Importamos tu archivo de error

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Atrapa cuando buscas algo que no existe (Error 404)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseError> handleNotFound(ResourceNotFoundException ex) {
        ApiResponseError error = new ApiResponseError(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // 2. Atrapa errores de validación (Error 400) 
    // Por ejemplo: si mandas un campo vacío que es obligatorio
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError> handleValidation(MethodArgumentNotValidException ex) {
        List<String> errores = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());
        
        ApiResponseError error = new ApiResponseError("Error en los datos enviados");
        error.setData(errores); // Guardamos la lista de errores en el campo data
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // 3. Atrapa cualquier otro error inesperado (Error 500)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseError> handleGlobal(Exception ex) {
        ApiResponseError error = new ApiResponseError("Ocurrió un error interno en el servidor");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}