package com.ista.springboot.final_com.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ista.springboot.final_com.models.dao.EstudianteDao;
import com.ista.springboot.final_com.models.entity.Estudiante;

@Service
public class EstudianteServiceImpl extends GenericServiceImpl<Estudiante, Long> implements IEstudianteService{

    @Autowired
    private EstudianteDao estudianteDao;

    @Override
    public CrudRepository<Estudiante, Long> getDao() {
        return estudianteDao;
    }
    
    @Override
    public List<Estudiante> findByNombreOrApellidoOrCedulaOrMateria(String keyword) {
        return estudianteDao.findByNombreContainingOrApellidoContainingOrCedulaContainingOrMateriaNombreContaining(keyword, keyword, keyword, keyword);
    }
    
}
