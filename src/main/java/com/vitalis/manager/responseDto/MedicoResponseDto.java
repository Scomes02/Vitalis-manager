package com.vitalis.manager.responseDto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

// @JsonPropertyOrder: Es un toque de "cosmética". 
// Le dice a Jackson (la librería que crea el JSON) en qué orden querés que aparezcan los campos.
// Sin esto, el JSON podría salir desordenado (ej: activo primero, id al final).
@JsonPropertyOrder({ "id", "nombre", "apellido", "dni",  "matricula", "email", "telefono", "especialidad", "consultorios", "activo", "version" })
public class MedicoResponseDto {
    
    // El ID va acá porque cuando respondemos, el objeto ya existe en la BD.
    private Long id;
    private String nombre;
    private String apellido;
    private String matricula;
    private String especialidad;
    private boolean activo;
    private Integer consultorios;
    private String dni;
    private String email;
    private String telefono;
    
    public Integer getConsultorios() {
		return consultorios;
	}
	public void setConsultorios(Integer consultorios) {
		this.consultorios = consultorios;
	}
	// Este campo es solo de lectura para el usuario (lo genera el backend/mapper).
    private int version; 
    
    // --- GETTERS Y SETTERS ---
    // Necesarios para que el Mapper pueda escribir datos aquí 
    // y para que Spring pueda leerlos para armar el JSON.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
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
    
    public int getVersion() { return version; }
    public void setVersion(int version) { this.version = version; }
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