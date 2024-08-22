package com.ista.springboot.final_com.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 3, max = 20, message = "El nombre de usuario debe tener entre 3 y 20 caracteres")
	private String nombreUsuario;
    
	@NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
	private String contrasena;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String nombre;
	
	@NotBlank(message = "El apellido es obligatorio")
	@Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
	private String apellido;
	
	 @NotBlank(message = "El email es obligatorio")
	 @Email(message = "El email debe ser válido")
	private String email;

	 
	@ManyToOne
	@JoinColumn(name = "rol_id")
	private Rol rol;
	
	
	// Getters y setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return this.rol;}
	
	public void setRol(Rol rol) {
		this.rol = rol;}


	
}

