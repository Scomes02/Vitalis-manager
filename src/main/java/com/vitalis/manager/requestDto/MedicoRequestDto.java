package com.vitalis.manager.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MedicoRequestDto {
	    private String especialidad;
	    private boolean activo;
	    
	    @NotBlank(message = "El nombre no puede estar en blanco")
	    private String nombre;

	    @NotBlank(message = "El apellido no puede estar en blanco")
	    private String apellido;

	    // ESTA es la que te está fallando ahora
	    @Size(min=4, message = "La matrícula debe tener al menos 4 caracteres")
	    private String matricula; 
	    
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
		public String getMatricula() {
			return matricula;
		}
		public void setMatricula(String matricula) {
			this.matricula = matricula;
		}
		public String getEspecialidad() {
			return especialidad;
		}
		public void setEspecialidad(String especialidad) {
			this.especialidad = especialidad;
		}
		public boolean isActivo() {
			return activo;
		}
		public void setActivo(boolean activo) {
			this.activo = activo;
		}

}

