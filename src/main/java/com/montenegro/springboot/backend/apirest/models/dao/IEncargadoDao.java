package com.montenegro.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;

public interface IEncargadoDao extends CrudRepository<Encargado, Long> {

}
