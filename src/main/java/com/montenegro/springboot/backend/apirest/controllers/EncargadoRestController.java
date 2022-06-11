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
import org.springframework.security.access.annotation.Secured;
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

import com.montenegro.springboot.backend.apirest.models.entity.Encargado;
import com.montenegro.springboot.backend.apirest.models.services.IEncargadoService;

@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EncargadoRestController {
	
	@Autowired
	private IEncargadoService encargadoService;
	
	private final Logger log = LoggerFactory.getLogger(EncargadoRestController.class);
	
	//Devuelve todos los encargados
	@Secured({"ROLE_ADMIN", "ROLE_ENCARGADO", "ROLE_EMPLEADO"})
	@GetMapping("/encargados")
	public List<Encargado> index() {
		return encargadoService.findAll();
	}
	
	//Devuelve un encargado por su ID
	@Secured({"ROLE_ADMIN", "ROLE_ENCARGADO", "ROLE_EMPLEADO"})
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
	@Secured("ROLE_ADMIN")
	@PostMapping("/encargados")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Encargado encargado, BindingResult result) {
		
		Encargado encargadoNew = null;
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
		
		//Manejo de excepciones
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
	@Secured("ROLE_ADMIN")
	@PutMapping("/encargados/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Encargado encargado, BindingResult result ,@PathVariable Long id) {
		
		Encargado encargadoActual = encargadoService.findById(id);
		Encargado encargadoUpdated = null;
		
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
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/encargados/{id}")
	public ResponseEntity<?> delte(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		
		try {
			//Elimina foto asociada si la hay
			Encargado encargado = encargadoService.findById(id);
			String nombreFotoAnterior = encargado.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			encargadoService.delete(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al eliminar de la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		 response.put("mensaje", "Encargado eliminado con éxito");
		 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
	
	//Subida de imagen
	@Secured("ROLE_ADMIN")
	@PostMapping("/encargados/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
		Map<String, Object> response = new HashMap<>();
		
		Encargado encargado = encargadoService.findById(id);
		
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
			String nombreFotoAnterior = encargado.getFoto();
			
			if(nombreFotoAnterior != null && nombreFotoAnterior.length()>0) {
				Path rutaFotoAnterior = Paths.get("uploads").resolve(nombreFotoAnterior).toAbsolutePath();
				File archivoFotoAnterior = rutaFotoAnterior.toFile();
				
				if(archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
					archivoFotoAnterior.delete();
				}
			}
			
			
			encargado.setFoto(nombreArchivo);
			
			encargadoService.save(encargado);
			
			response.put("encargado", encargado);
			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
		}
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	//Método para mostrar la foto
	@GetMapping("/uploads/img/encargado/{nombreFoto:.+}")
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
