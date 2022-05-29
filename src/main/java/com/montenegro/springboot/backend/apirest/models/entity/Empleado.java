package com.montenegro.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "empleados")
public class Empleado implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false)
	private String nombre;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false)
	private String apellido;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false, unique=true)
	private String dni;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Date contratacion;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public Date getContratacion() {
		return contratacion;
	}

	public void setContratacion(Date contratacion) {
		this.contratacion = contratacion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
