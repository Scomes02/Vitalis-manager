package com.vitalis.manager.service;

import com.vitalis.manager.entity.Turno;
import com.vitalis.manager.mapper.TurnoMapper;
import com.vitalis.manager.repository.TurnoRepository;
import com.vitalis.manager.requestDto.TurnoRequestDto;
import com.vitalis.manager.responseDto.TurnoResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private TurnoMapper turnoMapper;

    public List<TurnoResponseDto> listarTodos() {
        return turnoRepository.findAll().stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TurnoResponseDto guardar(TurnoRequestDto dto) {
        Turno entidad = turnoMapper.toEntity(dto);
        Turno guardado = turnoRepository.save(entidad);
        return turnoMapper.toResponse(guardado);
    }

    public TurnoResponseDto actualizar(Long id, TurnoRequestDto dto) {
        return turnoRepository.findById(id).map(existente -> {
            existente.setFechaHora(dto.getFechaHora());
            existente.setMotivo(dto.getMotivo());
            return turnoMapper.toResponse(turnoRepository.save(existente));
        }).orElse(null); // Idealmente aquí lanzarías una excepción
    }

    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }
}