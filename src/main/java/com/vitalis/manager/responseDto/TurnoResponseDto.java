package com.vitalis.manager.responseDto;

import java.time.LocalDateTime;

public class TurnoResponseDto {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;
    
    // Agregamos información descriptiva de las relaciones
    private String nombreMedico;
    private String nombrePaciente;

    public TurnoResponseDto() {}

    // Getters y Setters existentes
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }

    // Nuevos Getters y Setters para que el Mapper pueda llenarlos
    public String getNombreMedico() { return nombreMedico; }
    public void setNombreMedico(String nombreMedico) { this.nombreMedico = nombreMedico; }

    public String getNombrePaciente() { return nombrePaciente; }
    public void setNombrePaciente(String nombrePaciente) { this.nombrePaciente = nombrePaciente; }
}