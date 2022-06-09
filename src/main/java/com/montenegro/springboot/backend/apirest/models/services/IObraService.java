package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;
import com.montenegro.springboot.backend.apirest.models.entity.Obra;

public interface IObraService {
	public List<Obra> findAll();
	
	public Obra findById(Long id);
	
	public Obra save(Obra obra);
	
	public void delete(Long id);
	
	public List<Encargado> findAllEncargados();
}
