package com.vitalis.manager.requestDto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class TurnoRequestDto {

    @NotNull(message = "La fecha y hora son obligatorias")
    @Future(message = "La fecha del turno debe ser una fecha futura")
    private LocalDateTime fechaHora;

    @NotBlank(message = "El motivo del turno es obligatorio")
    private String motivo;
    
    @NotNull(message = "El ID del médico es obligatorio")
    private Long idMedico;

    @NotNull(message = "El ID del paciente es obligatorio")
    private Long idPaciente;

    // --- AGREGALOS MANUALMENTE OTRA VEZ ---
    public Long getIdMedico() { return idMedico; }
    public void setIdMedico(Long idMedico) { this.idMedico = idMedico; }

    public Long getIdPaciente() { return idPaciente; }
    public void setIdPaciente(Long idPaciente) { this.idPaciente = idPaciente; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}