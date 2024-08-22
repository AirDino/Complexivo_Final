package com.ista.springboot.final_com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ista.springboot.final_com.models.entity.Estudiante;

public interface EstudianteDao extends JpaRepository<Estudiante, Long> {
        
   

	List<Estudiante> findByNombreContainingOrApellidoContainingOrCedulaContainingOrMateriaNombreContaining(String nombre, String apellido, String Cedula, String nombreMateria);

	


}
