package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import com.montenegro.springboot.backend.apirest.models.entity.Empresa;

public interface IEmpresaService {
	
	public List<Empresa> findAll();
	
	public Empresa save(Empresa empresa);
	
	public void delete(Long id);
}
