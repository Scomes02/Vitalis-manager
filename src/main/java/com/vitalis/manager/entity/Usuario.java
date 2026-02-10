package com.vitalis.manager.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Usuarios")
public class Usuario {

    @Id    // @Id: Marca este campo como la clave primaria (Primary Key).
    @GeneratedValue(strategy=GenerationType.IDENTITY)    // @GeneratedValue: Le dice a la BD que genere el ID automáticamente (Autoincremental).
    private Long id;
 
    @Column(nullable = false, unique = true)// unique = true: No permite que existan dos usuarios con el mismo nombre
    private String username;
    
    @Column(nullable = false)
    private String password;
    
    @Column(nullable = false)
    private String rol;
    
    
    //Getters and Setters
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}    
}
