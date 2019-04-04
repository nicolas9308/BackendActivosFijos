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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


@Entity
@Table(name = "areas")
public class Area implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	@Temporal(TemporalType.DATE)
	@NotEmpty(message = "La fecha no puede estar vacia")
	private Date creado;

	@Column(name = "creado_por")
	@NotEmpty(message = "El usuario es necesario para llevar un campo de control")
	private String creadoPor;

	@Column
	@Temporal(TemporalType.DATE)
	@NotEmpty(message = "La fecha no puede estar vacia")
	private Date actualizado;

	@Column(name = "actualizado_por")
	@NotEmpty(message = "El usuario es necesario para llevar un campo de control")
	private String actualizadoPor;

	@Column
	@NotEmpty(message = "No puede ser vacio")
	@Size(min = 4, max = 45, message = " El tamaño tiene que estar entre 4 y 45 caracteres")
	private String nombre;

	@Column
	@NotEmpty(message = "No puede ser vacio")
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
