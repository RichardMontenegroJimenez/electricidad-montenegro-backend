package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import com.montenegro.springboot.backend.apirest.models.entity.Obra;

public interface IObraService {
	public List<Obra> findAll();
}
