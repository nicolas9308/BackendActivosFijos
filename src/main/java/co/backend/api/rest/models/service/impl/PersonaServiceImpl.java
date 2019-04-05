package co.backend.api.rest.models.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.backend.api.rest.models.dao.IPersonaDao;
import co.backend.api.rest.models.entity.Persona;
import co.backend.api.rest.models.service.IPersonaService;

/**
 * Implementación de interfaz Service de los métodos necesarios para personas
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDao personaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return (List<Persona>) personaDao.findAll();
	}
	
}
