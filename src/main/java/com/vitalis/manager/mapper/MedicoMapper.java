package com.vitalis.manager.mapper;

import org.springframework.stereotype.Component;
import com.vitalis.manager.entity.Medico;
import com.vitalis.manager.requestDto.MedicoRequestDto;
import com.vitalis.manager.responseDto.MedicoResponseDto;

// @Component: Spring detecta esta clase y permite inyectarla con @Autowired en el Service.
@Component
public class MedicoMapper {

    // Convierte de DTO (entrada del usuario) a Entidad (Base de datos)
    public Medico toEntity(MedicoRequestDto dto) {
        Medico medico = new Medico();
        // Mapeo manual campo por campo
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setMatricula(dto.getMatricula());
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setActivo(dto.isActivo());
        medico.setConsultorios(dto.getConsultorios());
        medico.setDni(dto.getDni());
        medico.setEmail(dto.getEmail());
        medico.setTelefono(dto.getTelefono());
        // NO se setea el ID porque eso lo genera la base de datos automáticamente.
        return medico;
    }

    // Convierte de Entidad (Base de datos) a DTO (respuesta al usuario)
    public MedicoResponseDto toResponse(Medico entity) {
        MedicoResponseDto dto = new MedicoResponseDto();
        dto.setId(entity.getId()); // Acá sí va el ID, para que el usuario sepa cuál es.
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setMatricula(entity.getMatricula());
        dto.setEspecialidad(entity.getEspecialidad());
        dto.setActivo(entity.isActivo());
        dto.setConsultorios(entity.getConsultorios());
        dto.setDni(entity.getDni());
        dto.setEmail(entity.getEmail());
        dto.setTelefono(entity.getTelefono());
        // Regla de Negocio del TP: Inventamos un campo "version" en la salida.
        dto.setVersion(1); 
        return dto;
    }
}