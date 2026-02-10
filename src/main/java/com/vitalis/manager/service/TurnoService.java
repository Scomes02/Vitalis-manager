package com.vitalis.manager.service;

import com.vitalis.manager.entity.Medico;
import com.vitalis.manager.entity.Paciente;
import com.vitalis.manager.entity.Turno;
import com.vitalis.manager.mapper.TurnoMapper;
import com.vitalis.manager.repository.MedicoRepository;
import com.vitalis.manager.repository.PacienteRepository;
import com.vitalis.manager.repository.TurnoRepository;
import com.vitalis.manager.requestDto.TurnoRequestDto;
import com.vitalis.manager.responseDto.TurnoResponseDto;
import com.vitalis.manager.exception.ResourceNotFoundException; // IMPORTANTE
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private TurnoMapper turnoMapper;

    public List<TurnoResponseDto> listarTodos() {
        return turnoRepository.findAll().stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public TurnoResponseDto guardar(TurnoRequestDto dto) {
        // 1. Buscar y validar Médico con TU excepción
        Medico medico = medicoRepository.findById(dto.getIdMedico())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + dto.getIdMedico()));

        // 2. Buscar y validar Paciente con TU excepción
        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + dto.getIdPaciente()));

        Turno entidad = turnoMapper.toEntity(dto);
        entidad.setMedico(medico);
        entidad.setPaciente(paciente);

        Turno guardado = turnoRepository.save(entidad);
        return turnoMapper.toResponse(guardado);
    }

    public TurnoResponseDto actualizar(Long id, TurnoRequestDto dto) {
        // Validamos primero que el turno exista
        Turno existente = turnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con ID: " + id));

        existente.setFechaHora(dto.getFechaHora());
        existente.setMotivo(dto.getMotivo());
        
        if (dto.getIdMedico() != null) {
            Medico medico = medicoRepository.findById(dto.getIdMedico())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID: " + dto.getIdMedico()));
            existente.setMedico(medico);
        }
        
        if (dto.getIdPaciente() != null) {
            Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID: " + dto.getIdPaciente()));
            existente.setPaciente(paciente);
        }

        return turnoMapper.toResponse(turnoRepository.save(existente));
    }

    public void eliminar(Long id) {
        // Validamos antes de eliminar para que el 404 funcione aquí también
        if (!turnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: Turno no encontrado con ID: " + id);
        }
        turnoRepository.deleteById(id);
    }
    
    public List<TurnoResponseDto> buscarProximosDelMedico(Long medicoId){
    	List<Turno> turnos = turnoRepository.findByMedicoIdAndFechaHoraAfterOrderByFechaHoraAsc(
                medicoId, 
                LocalDateTime.now() // <--- El punto de corte es "AHORA"
        );
    	return turnos.stream()
                .map(turnoMapper::toResponse)
                .collect(Collectors.toList());
    }
    
    public List<TurnoResponseDto> obtenerTurnosPorUsuario(Long usuarioId) {
        // 1. Buscamos si existe un médico con ese usuario
        Medico medico = medicoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new RuntimeException("Este usuario no tiene un perfil de médico asignado."));

        // 2. Si lo encontramos, usamos el ID del médico para buscar sus turnos (reusamos la lógica que ya tenías)
        // Usamos LocalDateTime.now() para traer solo los futuros, o quitamos el filtro de fecha si querés todos.
        return turnoRepository.findByMedicoIdAndFechaHoraAfterOrderByFechaHoraAsc(
                medico.getId(), 
                LocalDateTime.now()
        ).stream()
         .map(turnoMapper::toResponse)
         .collect(Collectors.toList());
    }
}