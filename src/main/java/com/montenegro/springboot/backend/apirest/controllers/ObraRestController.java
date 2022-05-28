package com.montenegro.springboot.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping("/obras/{id}")
	public Obra show(@PathVariable Long id) {
		return obraService.findById(id);
	}
	
	@PostMapping("/obras")
	@ResponseStatus(HttpStatus.CREATED)
	public Obra create(@RequestBody Obra obra) {
		return obraService.save(obra);
	}
	
	@PutMapping("/obras/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Obra update(@RequestBody Obra obra, @PathVariable Long id) {
		Obra obraActual = obraService.findById(id);
		
		obraActual.setDenominacion(obra.getDenominacion());
		obraActual.setDireccion(obra.getDireccion());
		obraActual.setCiudad(obra.getCiudad());
		obraActual.setEncargado(obra.getEncargado());
		
		return obraService.save(obraActual);
	}
	
	@DeleteMapping("/obras/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delte(@PathVariable Long id) {
		obraService.delete(id);
	}
}
