package com.vitalis.manager.repository;

import com.vitalis.manager.entity.Medico;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// @Repository: Le dice a Spring "Esta clase maneja datos, inyectala donde haga falta".
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    
    // Al extender de JpaRepository, ya heredamos métodos gratis como:
    // save() (guardar), findAll() (traer todo), findById() (buscar por ID), deleteById() (borrar).

    // --- MÉTODOS MÁGICOS (Query Methods) ---
    // Spring lee el nombre del método y crea la consulta SQL automáticamente.
    
    // SQL generado aprox: SELECT * FROM medicos WHERE upper(especialidad) LIKE upper('%?%')
    // Containing: Busca coincidencia parcial (como el operador LIKE %texto%).
    // IgnoreCase: Ignora si está en mayúsculas o minúsculas.
    List<Medico> findByEspecialidadContainingIgnoreCase(String especialidad);

    // SQL generado aprox: SELECT * FROM medicos WHERE matricula = ?
    // Busca la coincidencia exacta de la matrícula.
    List<Medico> findByMatricula(String matricula);
    
    List<Medico> findByConsultorios(Integer consultorios);
    
    List<Medico>findByDni(String dni);
    
    List<Medico>findByEmail(String email);
    
 // Método mágico: Busca el médico cuyo campo 'usuario' tenga el ID que le pasamos
    Optional<Medico> findByUsuarioId(Long usuarioId);
}