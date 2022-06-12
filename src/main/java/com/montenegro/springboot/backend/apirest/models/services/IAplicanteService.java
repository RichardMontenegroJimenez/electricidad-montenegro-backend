package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import com.montenegro.springboot.backend.apirest.models.entity.Aplicante;

public interface IAplicanteService {
	
	public List<Aplicante> findAll();
	
	public Aplicante save(Aplicante aplicante);
	
	public void delete(Long id);
}