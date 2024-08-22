package com.ista.springboot.final_com.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ista.springboot.final_com.models.dao.MateriasDao;
import com.ista.springboot.final_com.models.entity.Materias;

@Service
public class MateriasServiceImpl extends GenericServiceImpl<Materias, Long> implements IMateriasService{

    @Autowired
    private MateriasDao materiasDao;

    @Override
    public CrudRepository<Materias, Long> getDao() {
        return materiasDao;
    }

    public List<Materias> findAll() {
        return materiasDao.findAll();
    }
    
    
}
