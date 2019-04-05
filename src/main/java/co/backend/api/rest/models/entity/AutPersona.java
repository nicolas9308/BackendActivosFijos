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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Tabla entidad para autenticación de personas, en carpeta resources encontraremos el crud
 * junto con documetación de cada campo
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Entity
@Table(name = "aut_personas")
public class AutPersona implements Serializable {

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

	@Column(unique=true)
	@NotNull(message = "Debes ingresar un usuario")
	private String usuario;
	
	@Column
	@NotNull(message = "Debes ingresar una clave")
	private String clave;
	
	@Column
	@NotNull(message = "No puede ser vacio")
	@Size(min = 4, max = 11, message = " El tamaño tiene que estar entre 4 y 11 caracteres")
	private String estado;
	
	@ManyToMany(fetch= FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinTable(name="aut_personas_roles", joinColumns=@JoinColumn(name="aut_personas_id"), inverseJoinColumns=@JoinColumn(name="rol_id"),
	uniqueConstraints= {@UniqueConstraint(columnNames= {"aut_personas_id","rol_id"})})
	private List<Rol>roles;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id")
	private Persona persona;
	
	public AutPersona() {
		roles = new ArrayList<>();
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}
