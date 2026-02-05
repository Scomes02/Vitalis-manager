package com.vitalis.manager.responseDto;

import java.time.LocalDateTime;

public class TurnoResponseDto {
    private Long id;
    private LocalDateTime fechaHora;
    private String motivo;

    public TurnoResponseDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}