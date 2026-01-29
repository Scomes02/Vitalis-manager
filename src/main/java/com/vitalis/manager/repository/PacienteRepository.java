package com.vitalis.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitalis.manager.entity.Paciente;

@Repository

public interface PacienteRepository extends JpaRepository<Paciente,Long>{

}
