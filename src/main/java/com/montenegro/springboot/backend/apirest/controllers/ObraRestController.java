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
import org.springframework.validation.BindingResult;
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
	
	//Devuelve todas las obras
	@GetMapping("/obras")
	public List<Obra> index() {
		return obraService.findAll();
	}
	
	//Devuelve una obra por su ID
	@GetMapping("/obras/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Obra obra = null;
		Map<String, Object> response = new HashMap<>();
		
		//manejo de errores
		try {
			 obra = obraService.findById(id);
			 
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//Si el id no existe en la BBDD devuelve un error
		if (obra == null) {
			response.put("mensaje", "La obra ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Obra>(obra, HttpStatus.OK);
	}
	
	//Crear una obra
	@PostMapping("/obras")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Obra obra, BindingResult result) {
		
		Obra obraNew = null;
		Map<String, Object> response = new HashMap<>();
		
		//Validación
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			obraNew = obraService.save(obra);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La obra ha sido creado con éxito");
		response.put("obra", obraNew);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Actualizar obra por ID
	@PutMapping("/obras/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Obra obra, BindingResult result, @PathVariable Long id) {
		
		Obra obraActual = obraService.findById(id);
		Obra obraUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		//Validación
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		
		//Si el id no existe en la BBDD devuelve un error
		if (obraActual == null) {
			response.put("mensaje", "Error: no se pudo editar, la obra ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Manejo de errores
		try {
		obraActual.setDenominacion(obra.getDenominacion());
		obraActual.setDireccion(obra.getDireccion());
		obraActual.setCiudad(obra.getCiudad());
		obraActual.setEncargado(obra.getEncargado());
		
		obraUpdated = obraService.save(obraActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//Si no da error ejecuta la acutalización
		response.put("mensaje", "La obra ha sido actualizada con éxito");
		response.put("obra", obraUpdated);
		
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Eliminar obra por ID
	@DeleteMapping("/obras/{id}")
	public ResponseEntity<?> delte(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			obraService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "Obra eliminada con éxito");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
