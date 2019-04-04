package co.backend.api.rest.models.service;

import java.util.Date;
import java.util.List;
import co.backend.api.rest.models.entity.ActivoFijo;

public interface IActivoFijoService {

	public List<ActivoFijo> findAll();

	public Iterable<ActivoFijo> findByFiltro(Long tipoActivoFijo, Date fechaCompra, String serial);
	
	public ActivoFijo findById(Long id);
	
	public ActivoFijo save(ActivoFijo activoFijo);
	
}
