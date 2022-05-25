package com.montenegro.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;
import com.montenegro.springboot.backend.apirest.models.services.IEncargadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EncargadoRestController {
	
	@Autowired
	private IEncargadoService encargadoService;
	
	@GetMapping("/encargados")
	public List<Encargado> index() {
		return encargadoService.findAll();
	}
}
