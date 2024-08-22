package com.ista.springboot.final_com.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.springboot.final_com.models.entity.Estudiante;

@Service
public interface IEstudianteService extends GenericService<Estudiante, Long> {

	List<Estudiante> findByNombreOrApellidoOrCedulaOrMateria(String keyword);
	

	
}