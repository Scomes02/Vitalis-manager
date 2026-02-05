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

// @Service: Marca la clase como lógica de negocio para que Spring la administre.
@Service
public class MedicoService {

    @Autowired
    private MedicoRepository medicoRepository; // Acceso a BD

    @Autowired
    private MedicoMapper medicoMapper; // Acceso al traductor (DTO <-> Entidad)

    // Método inteligente para listar con filtros
    public List<MedicoResponseDto> listarTodos(String especialidad, String matricula, Integer consultorios, String dni, String email) {
        List<Medico> medicos;

        // 1. Prioridad ALTA: Si viene matrícula, buscamos exacto (porque es única).
        if (matricula != null && !matricula.isEmpty()) {
            medicos = medicoRepository.findByMatricula(matricula);
        } 
        // 2. Prioridad MEDIA: Si viene especialidad, buscamos aproximado.
        else if (especialidad != null && !especialidad.isEmpty()) {
            medicos = medicoRepository.findByEspecialidadContainingIgnoreCase(especialidad);
        } 
        // 3. Prioridad BAJA: Si no viene nada, traemos todos los registros.
        else if (consultorios != null){
            medicos = medicoRepository.findByConsultorios(consultorios);
            
        }else if(dni != null && !dni.isEmpty()){
        	medicos = medicoRepository.findByDni(dni);
        	
        }else if (email !=null && !email.isEmpty()){
        	medicos = medicoRepository.findByEmail(email);
        }else{
        	medicos = medicoRepository.findAll();
        }

        // Convertimos la lista de Entidades (BD) a lista de ResponseDTOs (JSON).
        return medicos.stream()
                .map(medico -> medicoMapper.toResponse(medico)) // Por cada médico, aplica el mapper
                .collect(Collectors.toList()); // Junta todo en una lista nueva
    }

    public MedicoResponseDto guardar(MedicoRequestDto dto) {
        // 1. Convertimos el DTO de entrada a Entidad
        Medico medico = medicoMapper.toEntity(dto);
        // 2. Guardamos en BD
        Medico guardado = medicoRepository.save(medico);
        // 3. Convertimos la Entidad guardada a DTO de salida
        return medicoMapper.toResponse(guardado);
    }

    public MedicoResponseDto actualizar(Long id, MedicoRequestDto dto) {
        // 1. Buscamos si existe. Si no, lanzamos excepción (Error 404).
        Medico medicoExistente = medicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        
        // 2. Pisamos los datos viejos con los nuevos que vienen en el DTO.
        medicoExistente.setNombre(dto.getNombre());
        medicoExistente.setApellido(dto.getApellido());
        medicoExistente.setMatricula(dto.getMatricula());
        medicoExistente.setEspecialidad(dto.getEspecialidad());
        medicoExistente.setConsultorios(dto.getConsultorios());
        medicoExistente.setDni(dto.getDni());
        medicoExistente.setEmail(dto.getEmail());
        medicoExistente.setActivo(dto.isActivo());
        medicoExistente.setTelefono(dto.getTelefono());

        // 3. Guardamos los cambios y devolvemos el DTO actualizado.
        return medicoMapper.toResponse(medicoRepository.save(medicoExistente));
    }

    public void eliminar(Long id) {
        // Borrado físico directo por ID.
        medicoRepository.deleteById(id);
    }
}