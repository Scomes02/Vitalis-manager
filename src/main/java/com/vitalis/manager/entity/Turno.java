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

    public Turno() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}
//@Entity y @Table: Le dicen a Spring Boot que esta clase representa una tabla en tu base de datos (se llamará turnos).
//@Id y @GeneratedValue: Definimos el ID como la clave primaria y le decimos que sea autoincremental (ideal para MySQL/PostgreSQL).
//Lombok (@Getter, @Setter, etc.): Usé estas anotaciones para que no tengas que escribir manualmente todos los métodos.