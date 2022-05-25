package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;

public interface IEncargadoService {
	public List<Encargado> findAll();
}
