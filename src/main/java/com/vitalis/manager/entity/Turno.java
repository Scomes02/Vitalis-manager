package com.vitalis.manager.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private String motivo;

    // --- AQUÍ CONECTAS CON EL TRABAJO DE TUS COMPAÑEROS ---
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico; // Clase creada por el Integrante B

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente; // Clase creada por el Integrante A

    // --- CONSTRUCTOR ---
    public Turno() {}

    // --- GETTERS Y SETTERS BÁSICOS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    // --- SETTERS PARA LAS RELACIONES (Los que te faltan en las fotos) ---
    // Sin estos, el Service no puede hacer "entidad.setMedico(medico)"
    
    public void setMedico(Medico medico) { 
        this.medico = medico; 
    }

    public void setPaciente(Paciente paciente) { 
        this.paciente = paciente; 
    }
    
    public Medico getMedico() { return medico; }
    public Paciente getPaciente() { return paciente; }
}
//@Entity y @Table: Le dicen a Spring Boot que esta clase representa una tabla en tu base de datos (se llamará turnos).
//@Id y @GeneratedValue: Definimos el ID como la clave primaria y le decimos que sea autoincremental (ideal para MySQL/PostgreSQL).
//Lombok (@Getter, @Setter, etc.): Usé estas anotaciones para que no tengas que escribir manualmente todos los métodos.