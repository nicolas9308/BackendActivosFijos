package co.backend.api.rest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.backend.api.rest.models.entity.Area;
import co.backend.api.rest.models.service.IAreaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Clase java de servicios RESTful para tabla de áreas<br>
 * Se elimina restricción cors para poder realizar conexion con servidor de angular
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 *
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
@Api(value = "Clase con todos los servicios necesarios para la tabla de areas")
public class AreaRestController {

	@Autowired
	private IAreaService areaService;

	protected Logger logger = LogManager.getLogger(AreaRestController.class);

	/**
	 * Método de obtencion de todas las áreas
	 * 
	 * @return ResponseEntity donde nos mapea un mensaje de control bien sea de éxito
	 *         o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception 
	 */
	@GetMapping("/areas")
	@ApiOperation(value = "Método de obtencion de todas las áreas")
	public ResponseEntity<?> getAll() throws Exception {
		Map<String, Object> response = new HashMap<>();
		
		List<Area> listaAreas = null;

		logger.info("Entra a: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		
		try {			
			listaAreas = areaService.findAll();
		}catch(DataAccessException e) {
			response.put("Mensaje", "Error al buscar las areas en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		logger.fatal("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		if (listaAreas.size() == 0) {
			response.put("Mensaje", "No se encontraron areas registradas");
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("mensaje", "Busqueda éxitosa");
			response.put("Lista_Areas", listaAreas);
			logger.info("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	}

}
