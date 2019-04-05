package co.backend.api.rest.models.service;

import java.util.Date;
import java.util.List;
import co.backend.api.rest.models.entity.ActivoFijo;

/**
 * Interfaz Service para matriculacion de los metodos necesarios para activos fijos
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface IActivoFijoService {

	public List<ActivoFijo> findAll() throws Exception;

	public Iterable<ActivoFijo> findByFiltro(Long tipoActivoFijo, Date fechaCompra, String serial) throws Exception;
	
	public ActivoFijo findById(Long id) throws Exception;
	
	public ActivoFijo save(ActivoFijo activoFijo) throws Exception;
	
	public void delete(Long id) throws Exception;
}
