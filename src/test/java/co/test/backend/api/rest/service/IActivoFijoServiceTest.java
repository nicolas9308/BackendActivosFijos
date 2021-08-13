package co.test.backend.api.rest.service;

import java.util.List;
import co.backend.api.rest.models.entity.ActivoFijo;

/**
 * Interfaz Service para matriculación de los metodos necesarios para activos fijos
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface IActivoFijoServiceTest {

	public List<ActivoFijo> findAll() throws Exception;

	public ActivoFijo findById(Long id) throws Exception;

}
