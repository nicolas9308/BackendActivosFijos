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
import co.backend.api.rest.models.entity.Persona;
import co.backend.api.rest.models.service.IPersonaService;

/**
 * clase java de servicios RESTful para tabla de personas<br>
 * Se elimina restriccion cors para poder realizar conexion con servidor de angular
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 *
 */
@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class PersonaRestController {

	@Autowired
	private IPersonaService personaService;

	protected Logger logger = LogManager.getLogger(PersonaRestController.class);

	/**
	 * Metodo de obtencion de todas las personas
	 * 
	 * @return ResponseEntity donde nos mapea un mesaje de control bien sea de exito
	 *         o de error y nuestro resultado en dado caso que se obtenga
	 * @throws Exception 
	 */
	@GetMapping("/personas")
	public ResponseEntity<?> getAll() throws Exception {
		Map<String, Object> response = new HashMap<>();
		List<Persona> listaPersonas = null;
		logger.fatal("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		try {			
			listaPersonas = personaService.findAll();
		}catch(DataAccessException e) {
			response.put("Mensaje", "Error al buscar las personas en la base de datos");
			response.put("Error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			logger.error("DataAccessException (" + Thread.currentThread().getStackTrace()[1].getMethodName() + ") :"
					+ e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (listaPersonas.size() == 0) {
			response.put("Mensaje", "No se encontraron personas registradas");
			logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		} else {
			response.put("mensaje", "Busqueda éxitosa");
			response.put("Lista_Personas", listaPersonas);
			logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		}
	}

}
