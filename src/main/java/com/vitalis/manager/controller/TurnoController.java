package com.vitalis.manager.controller;

import com.vitalis.manager.dto.ApiResponseSuccess;
import com.vitalis.manager.requestDto.TurnoRequestDto;
import com.vitalis.manager.responseDto.TurnoResponseDto;
import com.vitalis.manager.service.TurnoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    // 1. GET - Listar todos (Refactorizado)
    @GetMapping
    public ResponseEntity<ApiResponseSuccess<List<TurnoResponseDto>>> obtenerTodos() {
        List<TurnoResponseDto> lista = turnoService.listarTodos();
        return ResponseEntity.ok(new ApiResponseSuccess<>("Lista de turnos recuperada", lista));
    }

    // 2. POST - Crear (Ya lo tenías, lo mantenemos)
    @PostMapping
    public ResponseEntity<ApiResponseSuccess<TurnoResponseDto>> crear(@Valid @RequestBody TurnoRequestDto dto) {
        TurnoResponseDto creado = turnoService.guardar(dto);
        return new ResponseEntity<>(new ApiResponseSuccess<>("Turno creado con éxito", creado), HttpStatus.CREATED);
    }

    // 3. PUT - Actualizar (Refactorizado)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccess<TurnoResponseDto>> actualizar(
            @PathVariable Long id, 
            @Valid @RequestBody TurnoRequestDto dto) {
        TurnoResponseDto actualizado = turnoService.actualizar(id, dto);
        return ResponseEntity.ok(new ApiResponseSuccess<>("Turno actualizado correctamente", actualizado));
    }

    // 4. DELETE - Eliminar (Refactorizado)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccess<Void>> eliminar(@PathVariable Long id) {
        turnoService.eliminar(id);
        return ResponseEntity.ok(new ApiResponseSuccess<>("Turno eliminado con éxito", null));
    }
    
    // URL FINAL: GET http://localhost:8080/api/turnos/medico/1/proximos
    @GetMapping("/medico/{medicoId}/proximos")
    public ResponseEntity<ApiResponseSuccess<List<TurnoResponseDto>>> obtenerProximosPorMedico(
            @PathVariable Long medicoId
    ) {
        List<TurnoResponseDto> data = turnoService.buscarProximosDelMedico(medicoId);
        
        return ResponseEntity.ok(
            new ApiResponseSuccess<>("Agenda futura del médico obtenida", data)
        );
    }
    
 // GET http://localhost:8080/api/turnos/usuario/{usuarioId}
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<ApiResponseSuccess<List<TurnoResponseDto>>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        List<TurnoResponseDto> turnos = turnoService.obtenerTurnosPorUsuario(usuarioId);
        
        return ResponseEntity.ok(
            new ApiResponseSuccess<>("Turnos del usuario encontrados", turnos)
        );
    }
}