package com.montenegro.springboot.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;
import com.montenegro.springboot.backend.apirest.models.services.IEncargadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EncargadoRestController {
	
	@Autowired
	private IEncargadoService encargadoService;
	
	//Devuelve todos los encargados
	@GetMapping("/encargados")
	public List<Encargado> index() {
		return encargadoService.findAll();
	}
	
	//Devuelve un encargado por su ID
	@GetMapping("/encargados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Encargado encargado = null;
		Map<String, Object> response = new HashMap<>();
		
		//Manejo de erroes
		try {
			 encargado = encargadoService.findById(id);
			 
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//Si el id del encargado no existe en BBDD devuelve un error
		if (encargado == null) {
			response.put("mensaje", "El encargado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Encargado>(encargado, HttpStatus.OK);
	}
	
	//Crear un encargado
	@PostMapping("/encargados")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Encargado encargado) {
		
		Encargado encargadoNew = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			encargadoNew = encargadoService.save(encargado);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El encargado ha sido creado con éxito");
		response.put("encargado", encargadoNew);
		
		return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Actualizar encargado por ID
	@PutMapping("/encargados/{id}")
	public ResponseEntity<?> update(@RequestBody Encargado encargado, @PathVariable Long id) {
		
		Encargado encargadoActual = encargadoService.findById(id);
		Encargado encargadoUpdated = null;
		
		Map<String, Object> response = new HashMap<>();
		
		//Si el id no existe en la BBDD devuelve un error
		if (encargadoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el encargado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Manejo de errores
		try {
		encargadoActual.setNombre(encargado.getNombre());
		encargadoActual.setApellido(encargado.getApellido());
		encargadoActual.setDni(encargado.getDni());
		encargadoActual.setContratacion(encargado.getContratacion());
		
		encargadoUpdated = encargadoService.save(encargadoActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//Si no da error ejecuta la acutalización
		response.put("mensaje", "El encargado ha sido actualizada con éxito");
		response.put("encargado", encargadoUpdated);
		
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	
	//Eliminar encargado por ID
	@DeleteMapping("/encargados/{id}")
	public ResponseEntity<?> delte(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			encargadoService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "Encargado eliminado con éxito");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
