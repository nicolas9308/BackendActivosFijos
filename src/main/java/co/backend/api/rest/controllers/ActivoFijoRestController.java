package co.backend.api.rest.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.backend.api.rest.models.entity.ActivoFijo;
import co.backend.api.rest.models.entity.TipoActivo;
import co.backend.api.rest.models.service.IActivoFijoService;

/**
 * Clase java de servicios RESTful para tabla de activos fijos<br>
 * Se elimina restricción cors para poder realizar conexion con servidor de angular
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 *
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ActivoFijoRestController {

	protected Logger logger = LogManager.getLogger(ActivoFijoRestController.class);

	@Autowired
	private IActivoFijoService activoFijoService;

	/**
	 * Metodo de obtencion de todos los Tipos de activos fijos
	 * 
	 * @return ResponseEntity donde nos mapea un mensaje  de control bien sea de éxito
	 *         o de error y nuestro resultado en dado caso que se obtenga
	 */
	@GetMapping("/activosFijos")
	public ResponseEntity<?> getAll() {

		logger.info("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		Map<String, Object> response = new HashMap<>();
		List<ActivoFijo> listaActivos = null;
		try {
			listaActivos = activoFijoService.findAll();
		} catch (Exception e) {
			response.put("Mensaje", "Error al Buscar los tipos de activos en la base de datos");
			response.put("Error", e.getMessage().concat(":").concat(e.getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (listaActivos.size() == 0) {
			response.put("Mensaje", "No se encontraron activos");
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("mensaje", "Busqueda éxitosa");
			response.put("Lista_Activos", listaActivos);
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	}

	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/activosFijos/{id}")
	public ResponseEntity<?> getFinById(@PathVariable Long id) {

		logger.info("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		Map<String, Object> response = new HashMap<>();
		ActivoFijo listaActivos = null;
		try {
			listaActivos = activoFijoService.findById(id);
		} catch (Exception e) {
			response.put("Mensaje", "Error al Buscar el activo en la base de datos");
			response.put("Error", e.getMessage().concat(":").concat(e.getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (listaActivos == null) {
			response.put("Mensaje", "No se encontraron activos");
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("mensaje", "Busqueda éxitosa");
			response.put("Lista_Activos", listaActivos);
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	}
	
	/**
	 * Método  para filtrar los tipos de activos por un id, una fecha de compra y el
	 * número serial.
	 * 
	 * @param tipoActivo objeto entidad  que contiene fecha, id y serial para
	 *                   realizar búsqueda
	 * @return ResponseEntity donde nos mapea un mensaje de control bien sea de éxito
	 *         o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception 
	 */
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/activosFijosFiltro")
	public ResponseEntity<?> filtroActivosFijos(@RequestBody TipoActivo tipoActivo) throws Exception {

		logger.info("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		Map<String, Object> response = new HashMap<>();
		List<ActivoFijo> activosFijos = null;

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
			activosFijos = (List<ActivoFijo>) activoFijoService.findByFiltro(id, date, serial);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al Buscar el tipo de activo en la base de datos");
			response.put("Error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (activosFijos.size() == 0) {
			response.put("Mensaje", "No se encontraron activos");
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("mensaje", "Busqueda éxitosa");
			response.put("Tipo_Activo_Fijo", activosFijos);
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	}

	/**
	 * Método para eliminar un tipo de activo fijo a través del id
	 * 
	 * @param id del activo del cual realizaremos el borrado
	 * @return ResponseEntity donde nos mapea un mensaje de control bien sea de éxito
	 *         o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception 
	 */
	@Secured({"ROLE_ADMIN"})
	@DeleteMapping("/activosFijos/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {

		logger.info("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		Map<String, Object> response = new HashMap<>();

		try {

			ActivoFijo activoFijo = activoFijoService.findById(id);

			if (activoFijo == null) {
				response.put("Mensaje", "Error: no se pudo eliminar, el activo Id: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			activoFijoService.delete(id);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar el activo en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "El activo ha sido eliminado con éxito");
		logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	/**
	 * Método para actualizar un tipo de activo fijo a través de un id
	 * 
	 * @param activoFijo objeto entidad con las características que vamos a
	 *                   actualizar
	 * @param result     resultado de la validación de parámetros requeridos del
	 *                   objeto entidad activo fijo
	 * @param id         con el cual realizaremos la búsqueda del tipo de activo que
	 *                   vamos a actualizar
	 * @return ResponseEntity donde nos mapea un mesanje de control bien sea de éxito
	 *         o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception 
	 */
	@Secured({"ROLE_ADMIN"})
	@PutMapping("/activosFijos/{id}")
	public ResponseEntity<?> update(@Validated @RequestBody ActivoFijo activoFijo, BindingResult result,
			@PathVariable Long id) throws Exception {

		ActivoFijo activoFijoActual = activoFijoService.findById(id);
		ActivoFijo activoFijoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		logger.info("Entra a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "el campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("Error", errors);
			logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (activoFijoActual == null) {
			response.put("Mensaje", "Error: no se pudo editar, el activo Id: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			activoFijoActual.setCreado(activoFijoActual.getCreado());
			activoFijoActual.setEstado(activoFijo.getEstado());
			activoFijoActual.setNombre(activoFijo.getNombre());
			activoFijoActual.setDescripcion(activoFijo.getDescripcion());
			activoFijoActual.setActualizadoPor("0");
			activoFijoActual.setActualizado(new Date());

			activoFijoUpdated = activoFijoService.save(activoFijoActual);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar el activo en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "El activo ha sido actualizado con éxito");
		response.put("Tipo_Activo_Fijo", activoFijoUpdated);
		logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	/**
	 * Método de creación de un tipo de activo fijo
	 * 
	 * @param activoFijo objeto entidad del cual tomaremos los datos requeridos para
	 *                   hacer la inserción
	 * @param result     resultado de la validación de parámetros requeridos del
	 *                   objeto entidad activo fijo
	 * @return ResponseEntity donde nos mapea un mesanje de control bien sea de éxito
	 *         o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception 
	 */
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	@PostMapping("/activosFijos")
	public ResponseEntity<?> create(@Validated @RequestBody ActivoFijo activoFijo, BindingResult result) throws Exception {

		Map<String, Object> response = new HashMap<>();
		ActivoFijo activoFijoNew = null;

		logger.info("Entra a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "el campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("Error", errors);
			logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			activoFijoNew = activoFijoService.save(activoFijo);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar el insert en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "El activo ha sido creado con éxito");
		response.put("Tipo_Activo_Fijo", activoFijoNew);
		logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
