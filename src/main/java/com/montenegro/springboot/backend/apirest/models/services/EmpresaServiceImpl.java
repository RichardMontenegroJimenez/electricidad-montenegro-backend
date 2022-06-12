package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montenegro.springboot.backend.apirest.models.dao.IEmpresaDao;
import com.montenegro.springboot.backend.apirest.models.entity.Empresa;

@Service
public class EmpresaServiceImpl implements IEmpresaService{

	@Autowired
	private IEmpresaDao empresaDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Empresa> findAll() {
		return (List<Empresa>) empresaDao.findAll();
	}


	@Override
	@Transactional
	public Empresa save(Empresa empresa) {
		return empresaDao.save(empresa);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empresaDao.deleteById(id);
	}

}
