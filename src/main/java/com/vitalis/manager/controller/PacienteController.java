package com.vitalis.manager.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vitalis.manager.dto.request.PacienteRequestDto;
import com.vitalis.manager.dto.response.PacienteResponseDto;
import com.vitalis.manager.service.IPacienteService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pacientes")

public class PacienteController {

@Autowired
private IPacienteService pacienteService; 

@GetMapping
public ResponseEntity<List<PacienteResponseDto>> obtenerTodos() {
    return ResponseEntity.ok(pacienteService.listarTodos());
}

@PostMapping
public ResponseEntity<PacienteResponseDto> crear(@RequestBody PacienteRequestDto dto) {
    return new ResponseEntity<>(pacienteService.guardar(dto), HttpStatus.CREATED);
}

@PutMapping("/{id}")
public ResponseEntity<PacienteResponseDto> editar(@PathVariable Long id, @RequestBody PacienteRequestDto dto) {
    return ResponseEntity.ok(pacienteService.actualizar(id, dto));
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> borrar(@PathVariable Long id) {
    pacienteService.eliminar(id);
    return ResponseEntity.noContent().build();
}
	
	
	
}
