package com.montenegro.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.montenegro.springboot.backend.apirest.models.entity.Obra;

public interface IObraDao extends CrudRepository<Obra, Long> {

}
