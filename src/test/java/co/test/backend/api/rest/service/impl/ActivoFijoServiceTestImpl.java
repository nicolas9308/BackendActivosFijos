package co.test.backend.api.rest.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.backend.api.rest.models.entity.ActivoFijo;
import co.test.backend.api.rest.service.IActivoFijoServiceTest;

import static co.backend.api.rest.ActivoFijoDto.*;


/**
 * Implementación de interfaz Service de los métodos necesarios para activos fijos
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Service
public class ActivoFijoServiceTestImpl implements IActivoFijoServiceTest {

	protected Logger logger = LogManager.getLogger(ActivoFijoServiceTestImpl.class);

	@Override
	@Transactional(readOnly = true)
	public List<ActivoFijo> findAll() {
		List<ActivoFijo> activos = new ArrayList<ActivoFijo>();
		activos.add(activo001());
		activos.add(activo002());
		return activos;
	}
	
	@Override
	public ActivoFijo findById(Long id) {
		return activo001();
	}

}
