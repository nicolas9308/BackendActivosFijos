package co.backend.api.rest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.backend.api.rest.models.entity.AutPersona;

/**
 * Interfaz dao extendiendo de libreria crud repository para traer metodos
 * basicos, con opciones de extender JpaRepository para metodos de paginacion
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface IAutPersonaDao extends CrudRepository<AutPersona, Long> {

	@Query("select u from AutPersona u where u.usuario=?1")
	public AutPersona findByUsuario(String usuario);

}
