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

import com.montenegro.springboot.backend.apirest.models.entity.Empleado;
import com.montenegro.springboot.backend.apirest.models.services.IEmpleadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	@GetMapping("/empleados")
	public List<Empleado> index() {
		return empleadoService.findAll();
	}
	
	@GetMapping("/empleados/{id}")
	public Empleado show(@PathVariable Long id) {
		return empleadoService.findById(id);
	}
	
	@PostMapping("/empleados")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado create(@RequestBody Empleado empleado) {
		return empleadoService.save(empleado);
	}
	
	@PutMapping("/empleados/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Empleado update(@RequestBody Empleado empleado, @PathVariable Long id) {
		Empleado empleadoActual = empleadoService.findById(id);
		
		empleadoActual.setNombre(empleado.getNombre());
		empleadoActual.setApellido(empleado.getApellido());
		empleadoActual.setDni(empleado.getDni());
		empleadoActual.setContratacion(empleado.getContratacion());
		
		return empleadoService.save(empleadoActual);
	}
	
	@DeleteMapping("/empleados/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delte(@PathVariable Long id) {
		empleadoService.delete(id);
	}
}
