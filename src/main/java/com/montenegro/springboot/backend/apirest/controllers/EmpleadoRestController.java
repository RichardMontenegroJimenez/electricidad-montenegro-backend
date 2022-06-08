package com.montenegro.springboot.backend.apirest.controllers;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.montenegro.springboot.backend.apirest.models.entity.Empleado;
import com.montenegro.springboot.backend.apirest.models.services.IEmpleadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EmpleadoRestController {
	
	@Autowired
	private IEmpleadoService empleadoService;
	
	private final Logger log = LoggerFactory.getLogger(EmpleadoRestController.class);
	
	//Devuelve todos los empleados
	@GetMapping("/empleados")
	public List<Empleado> index() {
		return empleadoService.findAll();
	}
	
	//Devuelve un empleado por su ID
	@GetMapping("/empleados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Empleado empleado = null;
		Map<String, Object> response = new HashMap<>();
		
		//Manejo de errores
		try {
			 empleado = empleadoService.findById(id);
			 
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("erro", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		//Si el ID del empleado no existe en BBDD devuelve un error
		if (empleado == null) {
			response.put("mensaje", "El empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Empleado>(empleado, HttpStatus.OK);
	}
	
	//Crear un empleado
	@PostMapping("/empleados")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Empleado empleado, BindingResult result) {
		
		Empleado empleadoNew = null;
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
			empleadoNew = empleadoService.save(empleado);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El empleado ha sido creado con éxito");
		response.put("encargado", empleadoNew);
		
		return  new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Actualizar empleado por ID
	@PutMapping("/empleados/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Empleado empleado, BindingResult result, @PathVariable Long id) {
		
		Empleado empleadoActual = empleadoService.findById(id);
		Empleado empleadoUpdated = null;
		
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
		if (empleadoActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el empleado ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		//Manejo de errores
		try {
		empleadoActual.setNombre(empleado.getNombre());
		empleadoActual.setApellido(empleado.getApellido());
		empleadoActual.setDni(empleado.getDni());
		empleadoActual.setContratacion(empleado.getContratacion());
		
		empleadoUpdated = empleadoService.save(empleadoActual);
		
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		//Si no da error ejecuta la acutalización
		response.put("mensaje", "El empleado ha sido actualizada con éxito");
		response.put("empleado", empleadoUpdated);
		
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Eliminar encargado por ID
	@DeleteMapping("/empleados/{id}")
	public ResponseEntity<?> delte(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			
			//Elimina foto anterior si la hay
			Empleado empleado = empleadoService.findById(id);
			String nombreFotoAnterior = empleado.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			
			empleadoService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "Empleado eliminado con éxito");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//Subida de imagen
	@PostMapping("/empleados/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Empleado empleado = empleadoService.findById(id);
		
		if(!archivo.isEmpty()) {
			//Se crea un nombre unico
			String nombreArchivo = UUID.randomUUID().toString()  + "_" 
			+ archivo.getOriginalFilename().replace(" ", "");
			
			Path rutaArchivo = Paths.get("uploads").resolve(nombreArchivo).toAbsolutePath();
			log.info(rutaArchivo.toString());
			
			try {
				Files.copy(archivo.getInputStream(), rutaArchivo);
			} catch (IOException e) {
				response.put("mensaje", "Error al subir la imagen del encargado " + nombreArchivo);
				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			//Elimina foto anterior si la hay
			String nombreFotoAnterior = empleado.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			empleado.setFoto(nombreArchivo);
			
			empleadoService.save(empleado);
			
			response.put("empleado", empleado);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Método para mostrar la foto
	@GetMapping("/uploads/img/empleado/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
		
		Path rutaArchivo = Paths.get("uploads").resolve(nombreFoto).toAbsolutePath();
		log.info(rutaArchivo.toString());
		Resource recurso = null;
		
		try {
			recurso = new UrlResource(rutaArchivo.toUri());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		//Muestra por defecto imagen en caso de no existir
		if(!recurso.exists() && !recurso.isReadable()) {
			Path rutaArchivoNoExiste = Paths.get("src/main/resources/static/images").resolve("no-usuario.png").toAbsolutePath();
			
			try {
				recurso = new UrlResource(rutaArchivoNoExiste.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
			
			log.error("Error no se pudo cargar la imagen: " + nombreFoto);
		}
		
		
		
		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
		
		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
	}
	

}
