package com.ista.springboot.final_com.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ista.springboot.final_com.models.entity.Usuario;


public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    Usuario findByNombreUsuarioAndContrasena(String nombreUsuario, String contrasena);
    
    
    List<Usuario> findByNombreContainingOrApellidoContaining(String nombre, String apellido);
    
}