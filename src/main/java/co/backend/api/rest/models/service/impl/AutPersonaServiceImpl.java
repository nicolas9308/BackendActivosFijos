package co.backend.api.rest.models.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import co.backend.api.rest.models.dao.IAutPersonaDao;
import co.backend.api.rest.models.entity.AutPersona;
import co.backend.api.rest.models.service.IAutPersonaService;

/**
 * Implementación de interfaz Service de los métodos necesarios para autenticación de personas (AutPersonas)
 * 
 * @author Brayan Nicolas Peña Quintana
 * @version 0.0.1
 */
@Service
public class AutPersonaServiceImpl implements IAutPersonaService{

	private Logger logger = LoggerFactory.getLogger(AutPersonaServiceImpl.class);
	
	@Autowired
	private IAutPersonaDao autPersonaDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AutPersona autPersona = autPersonaDao.findByUsuario(username);
		
		if (autPersona ==null) {
			logger.error("error en el loggin: no existe el usuario '" + username + "' en el sistema");
			throw new UsernameNotFoundException("error en el loggin: no existe el usuario '" + username + "' en el sistema");
		}
		
		List<GrantedAuthority> authorities = autPersona.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
				.peek(autority -> logger.info("Rol: "+ autority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(username, autPersona.getClave(), true, true, true, true, authorities);
	}
	
	@Override
	@Transactional(readOnly = true)
	public AutPersona findByUsuario(String usuario) throws Exception {
		return autPersonaDao.findByUsuario(usuario);
	}

}
