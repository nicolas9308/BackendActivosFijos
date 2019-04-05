package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.ActivoFijo;

/**
 * Interfaz dao extendiendo de libreria crud repository para traer metodos
 * basicos, con opciones de extender JpaRepository para metodos de paginacion
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface IActivoFijoDao extends CrudRepository<ActivoFijo, Long> {

	public Iterable<ActivoFijo> findAll();

}
