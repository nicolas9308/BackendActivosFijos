package co.backend.api.rest.models.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.backend.api.rest.models.dao.IAreaDao;
import co.backend.api.rest.models.entity.Area;
import co.backend.api.rest.models.service.IAreaService;

/**
 * Implementación de interfaz Service de los métodos necesarios para áreas
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Service
public class AreaServiceImpl implements IAreaService {

	@Autowired
	private IAreaDao areaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Area> findAll() {
		return (List<Area>) areaDao.findAll();
	}
}
