package co.backend.api.rest.auth;

import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import co.backend.api.rest.controllers.ActivoFijoRestController;
import co.backend.api.rest.models.entity.AutPersona;
import co.backend.api.rest.models.service.IAutPersonaService;

/**
 * Clase java para agregar información adicional al token y validar el usuario
 * de la persona
 * 
 * @author Brayan Nicolas Pena Quintana
 * @version 0.0.1
 */
@Component
public class InfoAdicionalToken implements TokenEnhancer {

	protected Logger logger = LogManager.getLogger(ActivoFijoRestController.class);

	@Autowired
	private IAutPersonaService autPersonaService;
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

		Map<String, Object> info = new HashMap<>();
		AutPersona autPersona = null;
		logger.fatal("Entro a: " + Thread.currentThread().getStackTrace()[1].getMethodName());

		try {
			autPersona = autPersonaService.findByUsuario(authentication.getName());
			if (autPersona != null) {
				info.put("idPersona", autPersona.getId());
				info.put("usuario", autPersona.getUsuario());
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.fatal("Sale de: " + Thread.currentThread().getStackTrace()[1].getMethodName());
		return accessToken;
	}

}
