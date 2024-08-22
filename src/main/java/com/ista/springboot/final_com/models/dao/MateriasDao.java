package com.ista.springboot.final_com.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ista.springboot.final_com.models.entity.Materias;

public interface MateriasDao extends JpaRepository<Materias, Long> {

}