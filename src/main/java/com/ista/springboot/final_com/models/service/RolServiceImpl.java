package com.ista.springboot.final_com.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.ista.springboot.final_com.models.dao.RolDao;
import com.ista.springboot.final_com.models.entity.Rol;

@Service
public class RolServiceImpl extends GenericServiceImpl<Rol, Long> implements IRolService{

    @Autowired
    private RolDao rolDao;

    @Override
    public CrudRepository<Rol, Long> getDao() {
        return rolDao;
    }

    public List<Rol> findAll() {
        return rolDao.findAll();
    }
    
    
}
