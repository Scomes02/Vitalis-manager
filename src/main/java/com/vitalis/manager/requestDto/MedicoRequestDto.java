package com.vitalis.manager.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.*;
import jakarta.validation.constraints.Size;

public class MedicoRequestDto {
    
    // Estos campos no tienen validación obligatoria, pueden ser null o vacíos si querés.
    private String especialidad;
    private boolean activo;
    
    // @NotBlank: El portero. Si viene null o "", tira error 400 Bad Request.
    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "El apellido no puede estar en blanco")
    private String apellido;

    // @Size: Valida longitud. Si mandan "123", tira error 400 con este mensaje.
    // ESTO es lo que arregla el error 500 que tenías antes.
    @Size(min=4, message = "La matrícula debe tener al menos 4 caracteres")
    private String matricula; 

    @NotNull(message = "El número de consultorio es obligatorio")
    @Min(value = 1, message = "El consultorio debe ser un número positivo") // Opcional, pero recomendado
    private Integer consultorios;
    
    @NotNull(message = "El número del DNI es obligatorio")
    private String dni;
    
    @Email (message = "El mail no cumple con el formato")
    private String email;
    
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números")
    private String telefono;
    
	// --- GETTERS Y SETTERS ---
    // Sin esto, Spring no puede meter los datos del JSON adentro de este objeto.
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    
    public boolean isActivo() { return activo; }
    
    public void setActivo(boolean activo) { this.activo = activo; }    
    
    public Integer getConsultorios() {
		return consultorios;
	}
	public void setConsultorios(Integer consultorios) {
		this.consultorios = consultorios;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}