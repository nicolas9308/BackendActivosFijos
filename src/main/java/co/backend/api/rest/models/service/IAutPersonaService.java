package co.backend.api.rest.models.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import co.backend.api.rest.models.entity.AutPersona;

public interface IAutPersonaService extends UserDetailsService {

	public AutPersona findByUsuario(String usuario) throws Exception;

}
