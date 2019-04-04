package co.backend.api.rest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.backend.api.rest.models.entity.ActivoFijo;
import co.backend.api.rest.models.entity.TipoActivo;
import co.backend.api.rest.models.service.IActivoFijoService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ActivoFijoRestController {

	@Autowired
	private IActivoFijoService activoFijoService;

	@GetMapping("/activosFijos")
	public List<ActivoFijo> getAll() {
		return activoFijoService.findAll();
	}

	@PostMapping("/activosFijosFiltro")
	public ResponseEntity<?> filtro3(@RequestBody TipoActivo tipoActivo) {

		Map<String, Object> response = new HashMap<>();
		Iterable<ActivoFijo> activosFijos = null;

		Long id = null;
		Date date = null;
		String serial = null;

		if (tipoActivo.getActivoFijo() != null) {
			if (tipoActivo.getActivoFijo().getId() != null) {
				id = tipoActivo.getActivoFijo().getId();
			}
		}

		if (tipoActivo.getFechaCompra() != null) {
			date = tipoActivo.getFechaCompra();
		}

		if (tipoActivo.getSerial() != null) {
			serial = tipoActivo.getSerial();
		}

		try {
			activosFijos = activoFijoService.findByFiltro(id, date, serial);

		} catch (Exception e) {
			response.put("Mensaje", "Error al Buscar el tipo de activo en la base de datos");
			// response.put("error", e.getMessage().concat(":
			// ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// if (activosFijos == 0) {
		// response.put("mensaje", "No se encontraron activos");
		// return new ResponseEntity<Map<String, Object>>(response,
		// HttpStatus.NOT_FOUND);
		// } else {
		response.put("mensaje", "Busqueda éxitosa");
		response.put("Activos_Fijos", activosFijos);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		// }
	}
}
