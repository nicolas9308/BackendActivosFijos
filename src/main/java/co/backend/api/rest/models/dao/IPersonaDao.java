package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.Persona;

/**
 * Interfaz dao extendiendo de libreria crud repository para traer metodos
 * basicos, con opciones de extender JpaRepository para metodos de paginacion
 * 
 * @author Brayan Nicolas Peña Quintana
 *
 */
public interface IPersonaDao extends CrudRepository<Persona, Long> {

}
