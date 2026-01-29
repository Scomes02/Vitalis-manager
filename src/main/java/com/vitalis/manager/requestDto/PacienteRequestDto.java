package com.vitalis.manager.requestDto;

import java.time.LocalDate;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

public class PacienteRequestDto {

	private String nombre;
	private String apellido;
	
	@Pattern(regexp = "^[0-9]{7,8}$", message = "El DNI debe ser numérico y tener entre 7 y 8 dígitos")
	private String dni; 
	
	@Past(message = "La fecha debe ser anterior a la fecha actual")
	private LocalDate fechaNacimiento;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	} 
	
	
}
