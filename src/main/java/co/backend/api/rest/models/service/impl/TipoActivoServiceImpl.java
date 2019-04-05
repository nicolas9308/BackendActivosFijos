package co.backend.api.rest.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.backend.api.rest.models.dao.ITipoActivoDao;
import co.backend.api.rest.models.entity.TipoActivo;
import co.backend.api.rest.models.service.ITipoActivoService;

/**
 * implementacion de interfaz Service de los metodos necesarios para tipos de activos fijos
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Service
public class TipoActivoServiceImpl implements ITipoActivoService {

	@Autowired
	private ITipoActivoDao tipoActivoDao;

	@Override
	public TipoActivo findById(Long id) {
		return tipoActivoDao.findById(id).orElse(null);
	}

	@Override
	public TipoActivo save(TipoActivo tipoActivo) {
		return tipoActivoDao.save(tipoActivo);
	}

	@Override
	public void delete(Long id) {
		tipoActivoDao.deleteById(id);
	}

}
