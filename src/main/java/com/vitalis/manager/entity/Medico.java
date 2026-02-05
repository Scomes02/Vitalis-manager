package com.vitalis.manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

// @Entity: Indica que esta clase se debe convertir en una tabla de base de datos.
@Entity
// @Table: Define el nombre exacto de la tabla en la BD (si no se pone, usa el nombre de la clase).
@Table(name = "medicos")
public class Medico {

    // @Id: Marca este campo como la clave primaria (Primary Key).
    @Id
    // @GeneratedValue: Le dice a la BD que genere el ID automáticamente (Autoincremental).
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // Campos de la tabla. 
    // NOTA: Las validaciones (@NotBlank, @Size) aquí funcionan como restricciones de base de datos.
    // Si fallan aquí, lanzan error 500. Para error 400, recordá tenerlas también en el DTO.
    private String nombre;
    private String apellido;
    private String matricula;
    private String especialidad;
    private boolean activo; // true = trabajando, false = dado de baja lógica.
    private Integer consultorios;
    private String dni;
    private String email;
    private String telefono;
    
    // --- GETTERS Y SETTERS ---
    // Necesarios para que Spring y otras librerías puedan leer y escribir en estos campos privados.
    
    public Integer getConsultorios() {
		return consultorios;
	}
	public void setConsultorios(Integer consultorios) {
		this.consultorios = consultorios;
	}
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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