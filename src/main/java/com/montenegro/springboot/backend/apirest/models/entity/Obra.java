package com.montenegro.springboot.backend.apirest.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="obras")
public class Obra implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotEmpty(message = "no puede estar vacío")
	@Size(min=10, max=30, message="debe contener entre 10 y 50 caracteres")
	@Column(nullable=false)
	private String denominacion;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false)
	private String direccion;
	
	@NotEmpty(message = "no puede estar vacío")
	@Column(nullable=false)
	private String ciudad;
	
	@NotNull(message="no puede estar vacío")
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="encargado_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Encargado encargado;

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

	public Encargado getEncargado() {
		return encargado;
	}

	public void setEncargado(Encargado encargado) {
		this.encargado = encargado;
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
