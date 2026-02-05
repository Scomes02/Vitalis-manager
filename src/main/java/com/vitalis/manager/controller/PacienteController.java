package com.vitalis.manager.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitalis.manager.dto.ApiResponseSuccess;
import com.vitalis.manager.requestDto.PacienteRequestDto;
import com.vitalis.manager.responseDto.PacienteResponseDto;
import com.vitalis.manager.service.IPacienteService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;


 
@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccess<List<PacienteResponseDto>>> obtenerTodos(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) String domicilio,
            @RequestParam(required = false) String cel,
    		@RequestParam(required = false) String obraSocial ){

        List<PacienteResponseDto> data = pacienteService.listarTodos(id, dni, domicilio,cel, obraSocial);

        return ResponseEntity.ok(
                new ApiResponseSuccess<>("Lista de pacientes obtenida", data)
        );
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<PacienteResponseDto> obtenerPorDni(@PathVariable String dni) {
        return ResponseEntity.ok(pacienteService.buscarPorDni(dni));
    }
    
    @GetMapping("/cel/{cel}")
    public ResponseEntity<PacienteResponseDto> obtenerPorCel(@PathVariable String cel) {
        return ResponseEntity.ok(pacienteService.buscarPorCel(cel));
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDto> crear(
            @Valid @RequestBody PacienteRequestDto dto) {

        return new ResponseEntity<>(pacienteService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponseDto> editar(
            @PathVariable Long id,
            @Valid @RequestBody PacienteRequestDto dto) {

        return ResponseEntity.ok(pacienteService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        pacienteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
