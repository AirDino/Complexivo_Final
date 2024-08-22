package com.ista.springboot.final_com.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ista.springboot.final_com.models.dao.UsuarioDao;
import com.ista.springboot.final_com.models.entity.Usuario;


@Service
public class UsuarioServiceImpl extends GenericServiceImpl<Usuario, Long> implements IUsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public CrudRepository<Usuario, Long> getDao() {
        return usuarioDao;
    }
    
    @Override
    public Usuario findByUsernameAndPassword(String nombreUsuario, String contrasena) {
        return usuarioDao.findByNombreUsuarioAndContrasena(nombreUsuario, contrasena);
    }
    
    @Override
    public List<Usuario> findByNombreOrApellido(String keyword) {
        return usuarioDao.findByNombreContainingOrApellidoContaining(keyword, keyword);
    }
}
