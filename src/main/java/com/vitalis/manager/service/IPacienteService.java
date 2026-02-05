package com.vitalis.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitalis.manager.entity.Paciente;
import com.vitalis.manager.mapper.PacienteMapper;
import com.vitalis.manager.repository.PacienteRepository;
import com.vitalis.manager.requestDto.PacienteRequestDto;
import com.vitalis.manager.responseDto.PacienteResponseDto;

@Service
public class IPacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteMapper pacienteMapper;

    public List<PacienteResponseDto> listarTodos(Long id, String dni, String domicilio, String cel, String obraSocial) {

        // 🔹 Buscar por DNI
        if (dni != null && !dni.isBlank()) {
            Paciente paciente = pacienteRepository.findByDni(dni)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con DNI: " + dni));

            return List.of(pacienteMapper.toResponse(paciente));
        }

        // 🔹 Buscar por ID
        if (id != null) {
            Paciente paciente = pacienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + id));

            return List.of(pacienteMapper.toResponse(paciente));
        }

        
        if (domicilio != null && !domicilio.isBlank()) {
        	Paciente paciente =pacienteRepository.findByDomicilio(domicilio)
        			.orElseThrow(() -> new RuntimeException("Domicilio no encontrado"));
        	return List.of(pacienteMapper.toResponse(paciente));
        }
        
        if (cel != null && !cel.isBlank()) {
        	Paciente paciente =pacienteRepository.findByCel(cel)
        			.orElseThrow(() -> new RuntimeException("cel no encontrado"));
        	return List.of(pacienteMapper.toResponse(paciente));
        }
        
        
        
        // 🔹 Listar todos
        return pacienteRepository.findAll()
                .stream()
                .map(pacienteMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PacienteResponseDto buscarPorDni(String dni) {
        return pacienteRepository.findByDni(dni)
                .map(pacienteMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con DNI: " + dni));
    }
    
    public PacienteResponseDto buscarPorCel(String cel) {
        return pacienteRepository.findByDni(cel)
                .map(pacienteMapper::toResponse)
                .orElseThrow(() -> new RuntimeException("Cel no encontrado con Numero: " + cel));
    }

    public PacienteResponseDto guardar(PacienteRequestDto dto) {
        Paciente paciente = pacienteMapper.toEntity(dto);
        return pacienteMapper.toResponse(pacienteRepository.save(paciente));
    }

    public PacienteResponseDto actualizar(Long id, PacienteRequestDto dto) {
        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setDni(dto.getDni());
        paciente.setCel(dto.getCel());
        paciente.setDomicilio(dto.getDomicilio());
        paciente.setObraSocial(dto.getObraSocial());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        
        return pacienteMapper.toResponse(pacienteRepository.save(paciente));
    }

    public void eliminar(Long id) {
        pacienteRepository.deleteById(id);
    }

}

