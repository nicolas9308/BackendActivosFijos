package co.backend.api.rest.models.service;

import java.util.List;
import co.backend.api.rest.models.entity.Persona;

/**
 * Interfaz Service para matriculacion de los metodos necesarios para personas
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface IPersonaService {

	public List<Persona> findAll() throws Exception;

}
