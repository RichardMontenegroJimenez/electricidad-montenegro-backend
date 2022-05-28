package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montenegro.springboot.backend.apirest.models.dao.IEncargadoDao;
import com.montenegro.springboot.backend.apirest.models.entity.Encargado;

@Service
public class EncargadoServiceImpl implements IEncargadoService {

	@Autowired
	private IEncargadoDao encargadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Encargado> findAll() {
		
		return (List<Encargado>) encargadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Encargado findById(Long id) {
		
		return encargadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Encargado save(Encargado encargado) {
		
		return encargadoDao.save(encargado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		encargadoDao.deleteById(id);
		
	}


}
