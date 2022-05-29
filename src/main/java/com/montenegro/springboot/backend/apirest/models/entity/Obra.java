package com.montenegro.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="obras")
public class Obra implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=4, max=14, message="debe contener entre 4 y 14 caracteres")
	@Column(nullable=false)
	private String denominacion;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false)
	private String direccion;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false)
	private String ciudad;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false)
	private String encargado;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEncargado() {
		return encargado;
	}

	public void setEncargado(String encargado) {
		this.encargado = encargado;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
