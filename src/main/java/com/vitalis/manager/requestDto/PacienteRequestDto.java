package com.vitalis.manager.requestDto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PacienteRequestDto {

	private String nombre;
	private String apellido;
	
	@Pattern(regexp = "^[0-9]{7,8}$", message = "El DNI debe ser numérico y tener entre 7 y 8 dígitos")
	private String dni; 
	//@Pattern(regexp = "^[0-9]$", message = "El numero debe ser numérico")
	private String cel; 
	
	
	@NotBlank(message = "Debe colocar domicilio")
	@Size(min=5, max= 255, message = "pone domicilio!")
	private String domicilio; 
	private String obraSocial;
	

	@Past(message = "La fecha debe ser anterior a la fecha actual")
	private LocalDate fechaNacimiento;
	
	public String getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(String obraSocial) {
		this.obraSocial = obraSocial;
	}
	
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
	
	public String getCel() {
		return cel;
	}
	public void setCel(String cel) {
		this.cel = cel;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	} 
	
	
}
