package com.vitalis.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vitalis.manager.entity.Usuario;
import com.vitalis.manager.mapper.UsuarioMapper;
import com.vitalis.manager.repository.UsuarioRepository;
import com.vitalis.manager.requestDto.UsuarioRequestDto;
import com.vitalis.manager.responseDto.UsuarioResponseDto;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;

    public UsuarioResponseDto registrar(UsuarioRequestDto dto) {
        // Validar que no exista otro con el mismo nombre
        if (usuarioRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        
        Usuario usuario = usuarioMapper.toEntity(dto);
        return usuarioMapper.toResponse(usuarioRepository.save(usuario));
    }

    // Método especial para simular LOGIN
    public UsuarioResponseDto login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Comparación simple de texto (En producción esto se hace con Hash)
        if (!usuario.getPassword().equals(password)) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        return usuarioMapper.toResponse(usuario);
    }
}