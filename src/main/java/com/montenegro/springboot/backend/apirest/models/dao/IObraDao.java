package com.montenegro.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;
import com.montenegro.springboot.backend.apirest.models.entity.Obra;

public interface IObraDao extends CrudRepository<Obra, Long> {
	
	@Query("from Encargado")
	public List<Encargado> findAllEncargados();
	
}
