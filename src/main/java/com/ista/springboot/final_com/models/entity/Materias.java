package com.ista.springboot.final_com.models.entity;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="Materias")
public class Materias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	
	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 4, max = 20, message = "El nombre debe tener entre 3 y 20 caracteres")
	private String nombre;
	

	@OneToMany(mappedBy = "materia")
	private List<Estudiante> estudiante;


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


	public List<Estudiante> getEstudiante() {
		return estudiante;
	}


	public void setEstudiante(List<Estudiante> estudiante) {
		this.estudiante = estudiante;
	}
	
	
}
