package com.vitalis.manager.controller;

import com.vitalis.manager.requestDto.TurnoRequestDto;
import com.vitalis.manager.responseDto.TurnoResponseDto;
import com.vitalis.manager.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping
    public List<TurnoResponseDto> obtenerTodos() {
        return turnoService.listarTodos();
    }

    @PostMapping
    public TurnoResponseDto crear(@RequestBody TurnoRequestDto dto) {
        return turnoService.guardar(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> actualizar(@PathVariable Long id, @RequestBody TurnoRequestDto dto) {
        TurnoResponseDto actualizado = turnoService.actualizar(id, dto);
        return actualizado != null ? ResponseEntity.ok(actualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}