package com.vitalis.manager.mapper;

import com.vitalis.manager.entity.Paciente;
import com.vitalis.manager.requestDto.PacienteRequestDto;
import com.vitalis.manager.responseDto.PacienteResponseDto;

import org.springframework.stereotype.Component;

@Component
public class PacienteMapper {

    // Convierte del DTO que llega de la web a la Entidad para la base de datos
    public Paciente toEntity(PacienteRequestDto dto) {
        if (dto == null) {
            return null;
        }

        Paciente paciente = new Paciente();
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setDni(dto.getDni());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());

        return paciente;
    }

    // Convierte de la Entidad al DTO que se muestra al usuario
    public PacienteResponseDto toResponse(Paciente entity) {
        if (entity == null) {
            return null;
        }

        PacienteResponseDto dto = new PacienteResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setDni(entity.getDni());
        dto.setFechaNacimiento(entity.getFechaNacimiento());


        return dto;
    }
}
