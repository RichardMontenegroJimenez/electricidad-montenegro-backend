package com.montenegro.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name="empresas")
public class Empresa implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message ="no puede estar vacio")
	@Size(min=4, max=35, message="el tamaño tiene que estar entre 4 y 35")
	@Column(nullable=false)
	private String nombre;
	
	@NotEmpty(message ="no puede estar vacio")
	@Size(min=9, max=9, message="el tamaño debe ser de 9 caracteres")
	@Pattern(regexp="^([ABCDEFGHJKLMNPQRSUVW])(\\d{7})([0-9A-J])$", message="Introduce un formato valido de CIF,"
			+ " la letra debe estar en mayúscula")
	@Column(nullable=false, unique=true)
	private String cif;
	
	@NotEmpty(message ="no puede estar vacio")
	@Email(message="no es una dirección de correo bien formada")
	@Column(nullable=false, unique=true)
	private String email;
	
	@NotEmpty(message ="no puede estar vacio")
	@Size(min=10, max=255, message="no puede contener más de 255 carácteres ni menos de 10")
	@Column(nullable=false)
	private String text;
	
	@Column(name="create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@PrePersist
	public void prePersist() {
		createAt = new Date();
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	private static final long serialVersionUID = 1L;
}
