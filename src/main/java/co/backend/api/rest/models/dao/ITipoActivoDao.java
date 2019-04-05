package co.backend.api.rest.models.dao;

import org.springframework.data.repository.CrudRepository;
import co.backend.api.rest.models.entity.TipoActivo;

/**
 * Interfaz dao extendiendo de libreria crud repository para traer métodos
 * básicos, con opciones de extender JpaRepository para métodos de paginación
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface ITipoActivoDao extends CrudRepository<TipoActivo, Long> {
	
}
