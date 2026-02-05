package com.vitalis.manager.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitalis.manager.entity.Medico;
import com.vitalis.manager.mapper.MedicoMapper;
import com.vitalis.manager.repository.MedicoRepository;
import com.vitalis.manager.requestDto.MedicoRequestDto;
import com.vitalis.manager.responseDto.MedicoResponseDto;

@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private MedicoMapper medicoMapper;

    public List<MedicoResponseDto> listarTodos(String especialidad, String matricula) {
        List<Medico> medicos;

        // 1. Prioridad: ¿Viene matrícula? Buscamos exacto.
        if (matricula != null && !matricula.isEmpty()) {
            medicos = medicoRepository.findByMatricula(matricula);
        } 
        // 2. ¿Viene especialidad? Buscamos aproximado.
        else if (especialidad != null && !especialidad.isEmpty()) {
            medicos = medicoRepository.findByEspecialidadContainingIgnoreCase(especialidad);
        } 
        // 3. Si no viene nada, traemos todo.
        else {
            medicos = medicoRepository.findAll();
        }

        return medicos.stream()
                .map(medicoMapper::toResponse)
                .collect(Collectors.toList());
    }

    public MedicoResponseDto guardar(MedicoRequestDto dto) {
        Medico medico = medicoMapper.toEntity(dto);
        Medico guardado = medicoRepository.save(medico);
        return medicoMapper.toResponse(guardado);
    }

    public MedicoResponseDto actualizar(Long id, MedicoRequestDto dto) {
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        
        // Actualizamos los campos
        medicoExistente.setNombre(dto.getNombre());
        medicoExistente.setApellido(dto.getApellido());
        medicoExistente.setMatricula(dto.getMatricula());
        medicoExistente.setEspecialidad(dto.getEspecialidad());
        medicoExistente.setActivo(dto.isActivo());

        return medicoMapper.toResponse(medicoRepository.save(medicoExistente));
    }

    public void eliminar(Long id) {
        medicoRepository.deleteById(id);
    }
}