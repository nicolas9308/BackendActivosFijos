package co.backend.api.rest.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Tabla entidad para activos fijos, en carpeta resources encontraremos el crud
 * junto con documetación de cada campo
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Entity
@Table(name = "activos_fijos")
public class ActivoFijo implements Serializable {

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
	private String descripcion;

	@Column
	@NotNull(message = "No puede ser nula")
	@Size(min = 4, max = 11, message = " El tamaño tiene que estar entre 4 y 11 caracteres")
	private String estado;

	@JsonIgnoreProperties(value = { "activoFijo", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "activoFijo", cascade = CascadeType.ALL)
	private List<TipoActivo> tipoActivo;

	public ActivoFijo() {
		tipoActivo = new ArrayList<>();
	}

	public List<TipoActivo> getTipoActivo() {
		return tipoActivo;
	}

	public void setTipoActivo(List<TipoActivo> tipoActivo) {
		this.tipoActivo = tipoActivo;
	}

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
