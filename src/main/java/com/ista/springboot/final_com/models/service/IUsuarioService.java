package com.ista.springboot.final_com.models.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ista.springboot.final_com.models.entity.Usuario;

@Service
public interface IUsuarioService extends GenericService<Usuario, Long> {

	Usuario findByUsernameAndPassword(String nombreUsuario, String contrasena);

	List<Usuario> findByNombreOrApellido(String keyword);

	
}