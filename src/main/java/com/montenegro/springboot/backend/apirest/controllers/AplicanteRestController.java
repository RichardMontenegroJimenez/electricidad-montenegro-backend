package com.montenegro.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.montenegro.springboot.backend.apirest.models.entity.Aplicante;
import com.montenegro.springboot.backend.apirest.models.services.IAplicanteService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class AplicanteRestController {

	@Autowired
	private IAplicanteService aplicanteService;
	
	
	//Obtener aplicantes
	@Secured({"ROLE_ADMIN", "ROLE_ENCARGADO", "ROLE_EMPLEADO"})
	@GetMapping("/aplicantes")
	public List<Aplicante> index() {
		return aplicanteService.findAll();
	}
	
	//Crear solicitud aplicante
	@PostMapping("/aplicantes")
	public ResponseEntity<?> create(@Valid @RequestBody Aplicante aplicante, BindingResult result) {
		
		Aplicante aplicanteNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			aplicanteNew = aplicanteService.save(aplicante);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El aplicante ha sido creado con éxito!");
		response.put("aplicante", aplicanteNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Eliminar empresa por ID
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/aplicantes/{id}")
	public ResponseEntity<?> delte(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			aplicanteService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "aplicante eliminado con éxito");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	
	
}