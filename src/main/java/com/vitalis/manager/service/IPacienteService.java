package com.vitalis.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitalis.manager.dto.request.PacienteRequestDto;
import com.vitalis.manager.dto.response.PacienteResponseDto;
import com.vitalis.manager.entity.Paciente;
import com.vitalis.manager.mapper.PacienteMapper;
import com.vitalis.manager.repository.PacienteRepository;

@Service
public class IPacienteService {
	@Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;

    public List<PacienteResponseDto> listarTodos() {
        return pacienteRepository.findAll().stream()
                .map(pacienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponseDto guardar(PacienteRequestDto dto) {
        Paciente paciente = pacienteMapper.toEntity(dto);
        Paciente guardado = pacienteRepository.save(paciente);
        return pacienteMapper.toResponse(guardado);
    }

    public PacienteResponseDto actualizar(Long id, PacienteRequestDto dto) {
        Paciente pacienteExistente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        // Actualizamos los campos
        pacienteExistente.setNombre(dto.getNombre());
        pacienteExistente.setApellido(dto.getApellido());
        pacienteExistente.setDni(dto.getDni());
        pacienteExistente.setFechaNacimiento(dto.getFechaNacimiento());

        return pacienteMapper.toResponse(pacienteRepository.save(pacienteExistente));
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }
}
