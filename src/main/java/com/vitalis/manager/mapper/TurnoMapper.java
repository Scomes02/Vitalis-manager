package com.vitalis.manager.mapper;

import com.vitalis.manager.entity.Turno;
import com.vitalis.manager.requestDto.TurnoRequestDto;
import com.vitalis.manager.responseDto.TurnoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TurnoMapper {

    // Método para convertir de DTO a Entidad (para guardar en la DB)
    public Turno toEntity(TurnoRequestDto dto) {
        if (dto == null) return null;

        Turno entity = new Turno();
        entity.setFechaHora(dto.getFechaHora());
        entity.setMotivo(dto.getMotivo());
        
        // Nota: Los objetos 'Medico' y 'Paciente' se suelen setear en el Service 
        // buscando por ID, por eso acá solo mapeamos los datos básicos.
        
        return entity;
    }

    // Método para convertir de Entidad a Response DTO (para mostrar al usuario)
    public TurnoResponseDto toResponse(Turno entity) {
        if (entity == null) return null;

        TurnoResponseDto dto = new TurnoResponseDto();
        dto.setId(entity.getId());
        dto.setFechaHora(entity.getFechaHora());
        dto.setMotivo(entity.getMotivo());

        // --- CORRECCIÓN AQUÍ ---
        // Validamos que el médico no sea null para evitar errores (NullPointerException)
        if (entity.getMedico() != null) {
            // Concatenamos Nombre + espacio + Apellido
            String nombreCompletoMedico = entity.getMedico().getNombre() + " " + entity.getMedico().getApellido();
            dto.setNombreMedico(nombreCompletoMedico);
        }

        // Validamos que el paciente no sea null
        if (entity.getPaciente() != null) {
            // Concatenamos Nombre + espacio + Apellido
            String nombreCompletoPaciente = entity.getPaciente().getNombre() + " " + entity.getPaciente().getApellido();
            dto.setNombrePaciente(nombreCompletoPaciente);
        }
        // -----------------------

        return dto;
    }
}