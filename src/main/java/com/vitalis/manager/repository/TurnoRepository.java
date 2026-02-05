package com.vitalis.manager.repository;

import com.vitalis.manager.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
    // Aquí podrías agregar métodos personalizados más adelante
}