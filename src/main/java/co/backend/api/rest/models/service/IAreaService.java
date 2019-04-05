package co.backend.api.rest.models.service;

import java.util.List;

import co.backend.api.rest.models.entity.Area;

/**
 * Interfaz Service para matriculación de los metodos necesarios para areas
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
public interface IAreaService {

	public List<Area> findAll() throws Exception;

}
