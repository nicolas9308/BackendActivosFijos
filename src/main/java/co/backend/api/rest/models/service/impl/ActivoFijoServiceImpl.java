package co.backend.api.rest.models.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.backend.api.rest.models.dao.IActivoFijoDao;
import co.backend.api.rest.models.entity.ActivoFijo;
import co.backend.api.rest.models.entity.TipoActivo;
import co.backend.api.rest.models.service.IActivoFijoService;

@Service
public class ActivoFijoServiceImpl implements IActivoFijoService {

	@Autowired
	private IActivoFijoDao activoFijoDao;

	@Override
	@Transactional(readOnly = true)
	public List<ActivoFijo> findAll() {
		return (List<ActivoFijo>) activoFijoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<ActivoFijo> findByFiltro(Long tipoActivoFijo, Date fechaCompra, String serial) {

		Iterable<ActivoFijo> activoFijo = null;

		activoFijo = activoFijoDao.findAll();

		if (tipoActivoFijo != null) {
			activoFijo = ((Collection<ActivoFijo>) activoFijo).stream()
					.filter(activo -> activo.getId() == tipoActivoFijo).collect(Collectors.toList());
		}

		if (fechaCompra != null) {
			activoFijo = ((Collection<ActivoFijo>) activoFijo).stream().map(activo -> {
				List<TipoActivo> tipoActivo = activo.getTipoActivo();

				tipoActivo = tipoActivo.stream().filter(act -> fechaCompra.compareTo(act.getFechaCompra()) == 0)
						.collect(Collectors.toList());
				activo.setTipoActivo(tipoActivo);

				if (activo.getTipoActivo().size() == 0)
					return null;
				else
					return activo;
			}).filter(act -> act != null).collect(Collectors.toList());
		}

		if (serial != null) {
			activoFijo = ((Collection<ActivoFijo>) activoFijo).stream().map(activo -> {
				List<TipoActivo> tipoActivo = activo.getTipoActivo();

				tipoActivo = tipoActivo.stream().filter(act -> act.getSerial().equals(serial))
						.collect(Collectors.toList());
				activo.setTipoActivo(tipoActivo);

				if (activo.getTipoActivo().size() == 0)
					return null;
				else
					return activo;
			}).filter(act -> act != null).collect(Collectors.toList());
		}

		return activoFijo;
	}

	@Override
	public ActivoFijo save(ActivoFijo activoFijo) {
		return activoFijoDao.save(activoFijo);
	}

	@Override
	public ActivoFijo findById(Long id) {
		return activoFijoDao.findById(id).orElse(null);
	}

}
