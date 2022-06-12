package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montenegro.springboot.backend.apirest.models.dao.IAplicanteDao;
import com.montenegro.springboot.backend.apirest.models.entity.Aplicante;

@Service
public class AplicanteServiceImpl implements IAplicanteService{

	@Autowired
	private IAplicanteDao aplicanteDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Aplicante> findAll() {
		return (List<Aplicante>) aplicanteDao.findAll();
	}


	@Override
	@Transactional
	public Aplicante save(Aplicante aplicante) {
		return aplicanteDao.save(aplicante);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		aplicanteDao.deleteById(id);
	}

}