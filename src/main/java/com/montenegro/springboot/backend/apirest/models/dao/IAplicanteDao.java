package com.montenegro.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.montenegro.springboot.backend.apirest.models.entity.Aplicante;


public interface IAplicanteDao extends CrudRepository<Aplicante, Long> {

}