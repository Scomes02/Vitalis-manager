package com.vitalis.manager.repository;

import com.vitalis.manager.entity.Medico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // JpaRepository ya traer los métodos: save(), findAll(), findById(), deleteById(), etc.
	List<Medico> findByEspecialidadContainingIgnoreCase(String especialidad);
	List<Medico> findByMatricula(String matricula);
}
