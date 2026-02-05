package com.vitalis.manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vitalis.manager.dto.ApiResponseSuccess;
import com.vitalis.manager.requestDto.MedicoRequestDto;
import com.vitalis.manager.responseDto.MedicoResponseDto;
import com.vitalis.manager.service.MedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccess<List<MedicoResponseDto>>> obtenerTodos(
            @RequestParam(required = false) String especialidad,
            @RequestParam(required = false) String matricula // <--- AGREGAR ESTO
    ) {
        // Ahora le pasamos los DOS parámetros al servicio
        List<MedicoResponseDto> data = medicoService.listarTodos(especialidad, matricula);
        
        return ResponseEntity.ok(
            new ApiResponseSuccess<>("Lista de médicos obtenida", data)
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccess<MedicoResponseDto>> crear(@Valid @RequestBody MedicoRequestDto dto) {
        MedicoResponseDto resultado = medicoService.guardar(dto);
        return new ResponseEntity<>(
            new ApiResponseSuccess<>("Médico creado con éxito", resultado), 
            HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicoResponseDto> editar(@PathVariable Long id, @Valid @RequestBody MedicoRequestDto dto) {
        return ResponseEntity.ok(medicoService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        medicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}