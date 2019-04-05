package co.backend.api.rest.models.service;

import co.backend.api.rest.models.entity.TipoActivo;

/**
 * Interfaz Service para matriculación de los metodos necesarios para tipos de activos fijos
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface ITipoActivoService {

	public TipoActivo findById(Long id) throws Exception;

	public TipoActivo save(TipoActivo tipoActivo) throws Exception;

	public void delete(Long id) throws Exception;

}
