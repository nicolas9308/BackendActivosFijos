package co.backend.api.rest;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.WebApplicationInitializer;

/**
 * Clase configuracion server inicializer
 * 
 * @author Brayan Nicolas Pena Quintana
 * @version 0.0.1
 */
public class ServletInitializer extends SpringBootServletInitializer implements WebApplicationInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BackendApiRestActivosFijosApplication.class);
    }

}
