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
        
        return entity;
    }

    // Método para convertir de Entidad a Response DTO (para mostrar al usuario)
    public TurnoResponseDto toResponse(Turno entity) {
        if (entity == null) return null;

        TurnoResponseDto dto = new TurnoResponseDto();
        dto.setId(entity.getId());
        dto.setFechaHora(entity.getFechaHora());
        dto.setMotivo(entity.getMotivo());

        return dto;
    }
}