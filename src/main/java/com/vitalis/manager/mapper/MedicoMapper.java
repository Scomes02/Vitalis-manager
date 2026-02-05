package com.vitalis.manager.mapper;

import org.springframework.stereotype.Component;

import com.vitalis.manager.entity.Medico;
import com.vitalis.manager.requestDto.MedicoRequestDto;
import com.vitalis.manager.responseDto.MedicoResponseDto;

@Component
public class MedicoMapper {

	    public Medico toEntity(MedicoRequestDto dto) {
	        Medico medico = new Medico();
	        medico.setNombre(dto.getNombre());
	        medico.setApellido(dto.getApellido());
	        medico.setMatricula(dto.getMatricula());
	        medico.setEspecialidad(dto.getEspecialidad());
	        medico.setActivo(dto.isActivo());
	        return medico;
	    }

	    public MedicoResponseDto toResponse(Medico entity) {
	        MedicoResponseDto dto = new MedicoResponseDto();
	        dto.setId(entity.getId());
	        dto.setNombre(entity.getNombre());
	        dto.setApellido(entity.getApellido());
	        dto.setMatricula(entity.getMatricula());
	        dto.setEspecialidad(entity.getEspecialidad());
	        dto.setActivo(entity.isActivo());
	        dto.setVersion(1); // Regla del TP: version hardcodeada
	        return dto;
	    }
}

