package com.vitalis.manager.mapper;

import org.springframework.stereotype.Component;

import com.vitalis.manager.entity.Usuario;
import com.vitalis.manager.requestDto.UsuarioRequestDto;
import com.vitalis.manager.responseDto.UsuarioResponseDto;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequestDto dto) {
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.getUsername());
        usuario.setPassword(dto.getPassword());
        usuario.setRol(dto.getRol().toUpperCase()); // Forzamos mayúsculas (ej: ADMIN)
        return usuario;
    }

    public UsuarioResponseDto toResponse(Usuario entity) {
        UsuarioResponseDto dto = new UsuarioResponseDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRol(entity.getRol());
        return dto;
    }

}
