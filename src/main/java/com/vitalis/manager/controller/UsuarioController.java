package com.vitalis.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vitalis.manager.dto.ApiResponseSuccess;
import com.vitalis.manager.requestDto.UsuarioRequestDto;
import com.vitalis.manager.responseDto.UsuarioResponseDto;
import com.vitalis.manager.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. Crear Usuario (Registro)
    @PostMapping("/registro")
    public ResponseEntity<ApiResponseSuccess<UsuarioResponseDto>> registrar(@Valid @RequestBody UsuarioRequestDto dto) {
        UsuarioResponseDto resultado = usuarioService.registrar(dto);
        return new ResponseEntity<>(
            new ApiResponseSuccess<>("Usuario registrado con éxito", resultado),
            HttpStatus.CREATED
        );
    }

    // 2. Login (Devuelve el usuario si coincide pass)
    @PostMapping("/login")
    public ResponseEntity<ApiResponseSuccess<UsuarioResponseDto>> login(@RequestBody UsuarioRequestDto dto) {
        // Reusamos el DTO de request, aunque solo necesitamos username y password
        UsuarioResponseDto resultado = usuarioService.login(dto.getUsername(), dto.getPassword());
        return ResponseEntity.ok(
            new ApiResponseSuccess<>("Login exitoso. ¡Bienvenido!", resultado)
        );
    }
}