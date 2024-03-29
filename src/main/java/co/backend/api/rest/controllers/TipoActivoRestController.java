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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.backend.api.rest.models.entity.TipoActivo;
import co.backend.api.rest.models.service.ITipoActivoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Clase java de servicios RESTful para tabla de tipos de activos fijos<br>
 * Se elimina restricción cors para poder realizar conexión con servidor de
 * angular
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 *
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
@Api(value = "Clase con todos los servicios necesarios para la tabla de tipo activo")
public class TipoActivoRestController {

	@Autowired
	private ITipoActivoService tipoActivoService;

	protected Logger logger = LogManager.getLogger(TipoActivoRestController.class);

	/**
	 * Método de borrado para activos fijos
	 * 
	 * @param id id del tipo de activos fijos
	 * @return ResponseEntity donde nos mapea un mensaje de control bien sea de
	 *         éxito o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception
	 */
	@Secured({ "ROLE_ADMIN" })
	@DeleteMapping("/tiposActivosFijos/{id}")
	@ApiOperation(value = "Método de borrado para activos fijos")
	public ResponseEntity<?> delete(@PathVariable Long id) throws Exception {
		Map<String, Object> response = new HashMap<>();

		logger.info("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		try {

			TipoActivo tipoActivo = tipoActivoService.findById(id);

			if (tipoActivo == null) {
				response.put("Mensaje", "Error: no se pudo eliminar, el activo con tipo Id: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}

			tipoActivoService.delete(id);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al eliminar el activo en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "El activo ha sido eliminado con éxito");
		logger.info("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}

	/**
	 * Método de actualización para tipos de activos, actualizamos datos de
	 * auditoria estado, fecha de baja y serial
	 * 
	 * @param tipoActivo objeto entidad para obtener atributos fecha de baja, estado
	 *                   y serial
	 * @param result     objeto result para validar campos obligatorios
	 * @param id         del tipo de activo que vamos a actualizar
	 * @return ResponseEntity donde nos mapea un mensaje de control bien sea de
	 *         éxito o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception
	 */
	@Secured({ "ROLE_ADMIN" })
	@PutMapping("/tiposActivosFijos/{id}")
	@ApiOperation(value = "Método de actualización para tipos de activos, actualizamos datos de auditoria estado, fecha de baja y serial")
	public ResponseEntity<?> update(@Validated @RequestBody TipoActivo tipoActivo, BindingResult result,
			@PathVariable Long id) throws Exception {

		TipoActivo tipoActivoActual = tipoActivoService.findById(id);
		TipoActivo tipoActivoUpdated = null;

		Map<String, Object> response = new HashMap<>();

		logger.info("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		try {
			if (result.hasErrors()) {

				List<String> errors = result.getFieldErrors().stream()
						.map(err -> "el campo '" + err.getField() + "' " + err.getDefaultMessage())
						.collect(Collectors.toList());

				response.put("Error", errors);
				logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
			}

			if (tipoActivoActual == null) {
				response.put("Mensaje", "Error: no se pudo editar, el activo con tipo Id: "
						.concat(id.toString().concat(" no existe en la base de datos!")));
				logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			}
			if (tipoActivoActual.getFechaBaja() != null) {
				if (tipoActivoActual.getFechaCompra().compareTo(tipoActivo.getFechaBaja()) == 1) {
					response.put("Mensaje", "Error: no se pudo editar, el activo con tipo Id: ".concat(id.toString()
							.concat(" la fecha de baja no puede ser inferior o igual a la fecha de compra.")));
					logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
					return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
				}
			}

			tipoActivoActual.setCreado(tipoActivoActual.getCreado());
			tipoActivoActual.setCreadoPor(tipoActivoActual.getCreadoPor());
			tipoActivoActual.setAlto(tipoActivoActual.getAlto());
			tipoActivoActual.setActualizado(new Date());
			tipoActivoActual.setActualizadoPor("0");
			tipoActivoActual.setAncho(tipoActivoActual.getAncho());
			tipoActivoActual.setArea(tipoActivoActual.getArea());
			tipoActivoActual.setColor(tipoActivoActual.getColor());
			tipoActivoActual.setDescripcion(tipoActivoActual.getDescripcion());
			tipoActivoActual.setEstado(tipoActivo.getEstado());
			tipoActivoActual.setFechaBaja(tipoActivo.getFechaBaja());
			tipoActivoActual.setFechaCompra(tipoActivoActual.getFechaCompra());
			tipoActivoActual.setLargo(tipoActivoActual.getLargo());
			tipoActivoActual.setNombre(tipoActivoActual.getNombre());
			tipoActivoActual.setNumeroInventario(tipoActivoActual.getNumeroInventario());
			tipoActivoActual.setPeso(tipoActivoActual.getPeso());
			tipoActivoActual.setSerial(tipoActivo.getSerial());
			tipoActivoActual.setValorCompra(tipoActivoActual.getValorCompra());

			tipoActivoUpdated = tipoActivoService.save(tipoActivoActual);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al actualizar el activo en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "El activo ha sido actualizado con éxito");
		response.put("Activo_Fijo", tipoActivoUpdated);
		logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	/**
	 * Método de creación para tipo de activo fijo
	 * 
	 * @param tipoActivo objeto entidad para obtener atributos
	 * @param result     objeto result para validar campos obligatorios
	 * @return ResponseEntity donde nos mapea un mensaje de control bien sea de
	 *         éxito o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception
	 */
	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	@PostMapping("/tiposActivosFijos")
	@ApiOperation(value = "Método de creación para tipo de activo fijo")
	public ResponseEntity<?> create(@Validated @RequestBody TipoActivo tipoActivo, BindingResult result)
			throws Exception {

		Map<String, Object> response = new HashMap<>();
		TipoActivo tipoActivoNew = null;

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

			tipoActivoNew = tipoActivoService.save(tipoActivo);

		} catch (DataAccessException e) {
			response.put("Mensaje", "Error al realizar el insert en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("Mensaje", "El activo ha sido creado con éxito");
		response.put("Activo_Fijo", tipoActivoNew);
		logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

}
