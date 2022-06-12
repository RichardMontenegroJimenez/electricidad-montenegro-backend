package com.montenegro.springboot.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.montenegro.springboot.backend.apirest.models.entity.Empresa;


public interface IEmpresaDao extends CrudRepository<Empresa, Long> {

}
