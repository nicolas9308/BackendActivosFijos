package co.backend.api.rest.models.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Tabla entidad para tipos activos fijos, en carpeta resources encontraremos el crud
 * junto con documetación de cada campo
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Entity
@Table(name = "tipos_activos")
public class TipoActivo implements Serializable{
	
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
	@NotNull(message = "No puede ser vacio")
	@Size(min = 4, max = 50, message = " El tamaño tiene que estar entre 4 y 50 caracteres")
	private String nombre;

	@Column
	private String descripcion;
	
	@Column
	@NotNull(message = "No puede ser nula")
	@Size(min = 4, max = 45, message = " El tamaño tiene que estar entre 4 y 45 caracteres")
	private String serial;
	
	@Column(name="numero_inventario")
	@NotNull(message = "No puede ser nula")
	@Size(min = 3, max = 40, message = " El tamaño tiene que estar entre 3 y 40 caracteres")
	private String numeroInventario;
	
	@Column
	private BigDecimal peso;
	
	@Column
	private BigDecimal alto;
	
	@Column
	private BigDecimal ancho;
	
	@Column
	private BigDecimal largo;
	
	@Column(name="valor_compra")
	@NotNull(message = "No puede ser nula")
	private BigDecimal valorCompra;
	
	@Column(name="fecha_compra")
	@Temporal(TemporalType.DATE)
	@NotNull(message = "La fecha no puede estar vacia")
	private Date fechaCompra;
	
	@Column(name="fecha_baja")
	@Temporal(TemporalType.DATE)
	private Date fechaBaja;
	
	@Column
	@NotNull(message = "No puede ser nula")
	@Size(min = 4, max = 11, message = " El tamaño tiene que estar entre 4 y 11 caracteres")
	private String estado;
	
	@Column
	@Size(min = 4, max = 10, message = " El tamaño tiene que estar entre 4 y 10 caracteres")
	private String color;
	
	@JsonIgnoreProperties(value= {"tipoActivo", "hibernateLazyInitializer", "handler" }, allowSetters = true)
	@ManyToOne(fetch = FetchType.LAZY)
	private ActivoFijo activoFijo;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "area_id")
	private Area area;
	
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "persona_id")
	private Persona persona;

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

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNumeroInventario() {
		return numeroInventario;
	}

	public void setNumeroInventario(String numeroInventario) {
		this.numeroInventario = numeroInventario;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getAlto() {
		return alto;
	}

	public void setAlto(BigDecimal alto) {
		this.alto = alto;
	}

	public BigDecimal getAncho() {
		return ancho;
	}

	public void setAncho(BigDecimal ancho) {
		this.ancho = ancho;
	}

	public BigDecimal getLargo() {
		return largo;
	}

	public void setLargo(BigDecimal largo) {
		this.largo = largo;
	}

	public BigDecimal getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(BigDecimal valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public Date getFechaBaja() {
		return fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ActivoFijo getActivoFijo() {
		return activoFijo;
	}

	public void setActivoFijo(ActivoFijo activoFijo) {
		this.activoFijo = activoFijo;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
}
