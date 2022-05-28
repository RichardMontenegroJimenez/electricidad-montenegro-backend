package com.montenegro.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="obras")
public class Obra implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable=false)
	private String denominacion;
	@Column(nullable=false)
	private String direccion;
	@Column(nullable=false)
	private String ciudad;
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
