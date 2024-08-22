package com.ista.springboot.final_com.models.entity;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="estudiantes", uniqueConstraints = {@UniqueConstraint(columnNames = {"cedula"})})
public class Estudiante implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "La cédula es obligatoria")
	@Pattern(regexp = "^[0-9]{10}$", message = "La cédula debe tener 10 dígitos numéricos")
	private String cedula; 
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 3, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
    private String nombre;
	
	@NotBlank(message = "El apellido es obligatorio")
	@Size(min = 3, max = 20, message = "El apellido debe tener entre 3 y 20 caracteres")
	private String apellido;
	
	@NotBlank(message = "La dirección es obligatoria")
	@Size(min = 3, max = 20, message = "La dirección debe tener entre 5 y 40 caracteres")
	private String direccion; 
	
	@Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
	@NotNull(message = "La fecha de nacimiento es obligatoria")
	private LocalDate fecha_nacimiento; 
	
	 
	@ManyToOne
	@JoinColumn(name = "materia_id")
	private Materias materia;


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


	public Materias getMateria() {
		return materia;
	}


	public void setMateria(Materias materia) {
		this.materia = materia;
	}


	public String getCedula() {
		return cedula;
	}


	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public LocalDate getFecha_nacimiento() {
		return fecha_nacimiento;
	}


	public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

}
