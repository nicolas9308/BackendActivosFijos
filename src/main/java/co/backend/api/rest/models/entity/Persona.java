package co.backend.api.rest.models.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Tabla entidad para personas, en carpeta resources encontraremos el crud
 * junto con documetación de cada campo
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Entity
@Table(name = "personas")
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date creado;

	@Column(name = "creado_por")
	@NotNull(message = "El usuario es necesario para llevar un campo de control")
	private String creadoPor;

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	private Date actualizado;

	@Column(name = "actualizado_por")
	@NotNull(message = "El usuario es necesario para llevar un campo de control")
	private String actualizadoPor;
	
	@Column
	@NotNull(message = "No puede ser nula")
	@Size(min = 4, max = 60, message = " El tamaño tiene que estar entre 4 y 60 caracteres")
	private String nombre;

	@Column
	@Size(min = 4, max = 45, message = " El tamaño tiene que estar entre 4 y 45 caracteres")
	private String cargo;
	
	@Column
	@NotNull(message = "No puede ser nula")
	@Size(min = 4, max = 30, message = " El tamaño tiene que estar entre 4 y 30 caracteres")
	private String identificacion;
	
	@Column
	@Size(min = 4, max = 45, message = " El tamaño tiene que estar entre 4 y 45 caracteres")
	private String telefono;
	
	@Column
	@NotNull(message = "No puede ser nula")
	@Size(min = 4, max = 30, message = " El tamaño tiene que estar entre 4 y 30 caracteres")
	private String email;
	
	@Column
	@NotNull(message = "No puede ser nula")
	@Size(min = 4, max = 11, message = " El tamaño tiene que estar entre 4 y 11 caracteres")
	private String estado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreado() {
		return creado;
	}

	public void setCreado(Date creado) {
		this.creado = creado;
	}

	public String getCreadoPor() {
		return creadoPor;
	}

	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}

	public Date getActualizado() {
		return actualizado;
	}

	public void setActualizado(Date actualizado) {
		this.actualizado = actualizado;
	}

	public String getActualizadoPor() {
		return actualizadoPor;
	}

	public void setActualizadoPor(String actualizadoPor) {
		this.actualizadoPor = actualizadoPor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
