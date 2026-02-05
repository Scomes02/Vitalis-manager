package com.vitalis.manager.requestDto;

import java.time.LocalDateTime;

public class TurnoRequestDto {
    private LocalDateTime fechaHora;
    private String motivo;

    public TurnoRequestDto() {}

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}