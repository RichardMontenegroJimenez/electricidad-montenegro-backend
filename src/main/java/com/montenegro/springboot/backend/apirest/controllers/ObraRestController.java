package com.montenegro.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montenegro.springboot.backend.apirest.models.entity.Obra;
import com.montenegro.springboot.backend.apirest.models.services.IObraService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ObraRestController {
	
	@Autowired
	private IObraService obraService;
	
	@GetMapping("/obras")
	public List<Obra> index() {
		return obraService.findAll();
	}
}
