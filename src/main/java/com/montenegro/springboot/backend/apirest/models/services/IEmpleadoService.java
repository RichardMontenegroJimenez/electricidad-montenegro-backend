package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import com.montenegro.springboot.backend.apirest.models.entity.Empleado;

public interface IEmpleadoService {
	public List<Empleado> findAll();
}
