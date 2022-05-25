package com.montenegro.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.montenegro.springboot.backend.apirest.models.dao.IObraDao;
import com.montenegro.springboot.backend.apirest.models.entity.Obra;

@Service
public class ObraServiceImpl implements IObraService {

	@Autowired
	private IObraDao obraDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Obra> findAll() {
		
		return (List<Obra>) obraDao.findAll();
	}


}
