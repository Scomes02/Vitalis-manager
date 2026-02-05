package com.vitalis.manager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*; // Importa todas las anotaciones web
import com.vitalis.manager.dto.ApiResponseSuccess;
import com.vitalis.manager.requestDto.MedicoRequestDto;
import com.vitalis.manager.responseDto.MedicoResponseDto;
import com.vitalis.manager.service.MedicoService;
import jakarta.validation.Valid;

// @RestController: Convierte la clase en un endpoint REST que responde JSON.
@RestController
// @RequestMapping: Define la URL base. Todo lo de acá empieza con "localhost:8080/api/medicos".
@RequestMapping("/api/medicos")
public class MedicoController {

    // @Autowired: Inyección de Dependencias. Trae el Servicio listo para usar.
    @Autowired
    private MedicoService medicoService;

    // --- OBTENER (GET) ---
    @GetMapping
    public ResponseEntity<ApiResponseSuccess<List<MedicoResponseDto>>> obtenerTodos(
            // @RequestParam: Captura variables de la URL (ej: ?especialidad=cardio).
            // required = false: Hace que el parámetro sea opcional. Si no está, llega null.
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String matricula,
            @RequestParam(required = false) Integer consultorios,
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) String email
    ) {
        // Llamamos al servicio pasando los filtros (pueden ser null).
        List<MedicoResponseDto> data = medicoService.listarTodos(especialidad, matricula, consultorios, dni, email);
        
        // Envolvemos la respuesta en nuestro ApiResponseSuccess estandarizado.
        return ResponseEntity.ok(
            new ApiResponseSuccess<>("Lista de médicos obtenida", data)
        );
    }

    // --- CREAR (POST) ---
    @PostMapping
    public ResponseEntity<ApiResponseSuccess<MedicoResponseDto>> crear(
            // @Valid: Activa las validaciones del DTO (@NotBlank, etc.). Si falla, tira 400 Bad Request.
            // @RequestBody: Convierte el JSON que envías en Postman a un objeto Java (MedicoRequestDto).
            @Valid @RequestBody MedicoRequestDto dto
    ) {
        MedicoResponseDto resultado = medicoService.guardar(dto);
        
        // Retornamos 201 CREATED con el objeto creado.
        return new ResponseEntity<>(
            new ApiResponseSuccess<>("Médico creado con éxito", resultado), 
            HttpStatus.CREATED
        );
    }

    // --- ACTUALIZAR (PUT) ---
    @PutMapping("/{id}") // La URL incluye el ID, ej: /api/medicos/1
    public ResponseEntity<MedicoResponseDto> editar(
            @PathVariable Long id, // Captura el {id} de la URL
            @Valid @RequestBody MedicoRequestDto dto // Captura los datos nuevos del Body
    ) {
        // Llama al servicio para actualizar y devuelve el resultado actualizado.
        return ResponseEntity.ok(medicoService.actualizar(id, dto));
    }

    // --- BORRAR (DELETE) ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        medicoService.eliminar(id);
        // Retorna 204 NO CONTENT (Éxito, pero sin cuerpo de respuesta).
        return ResponseEntity.noContent().build();
    }
}