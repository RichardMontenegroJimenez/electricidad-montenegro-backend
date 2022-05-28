package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;


public interface IEncargadoService {
	public List<Encargado> findAll();
	
	public Encargado findById(Long id);
	
	public Encargado save(Encargado encargado);
	
	public void delete(Long id);
}
