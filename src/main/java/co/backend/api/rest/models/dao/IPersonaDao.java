package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;

import co.backend.api.rest.models.entity.Persona;

/**
 * Interfaz dao extendiendo de libreria crud repository para traer métodos
 * básicos, con opciones de extender JpaRepository para métodos de paginación
 * 
 * @author Brayan Nicolas Peña Quintana
 *
 */
public interface IPersonaDao extends CrudRepository<Persona, Long> {

}
