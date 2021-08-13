package co.backend.api.rest;

import java.util.Date;
import co.backend.api.rest.models.entity.ActivoFijo;

public class ActivoFijoDto {

	public static ActivoFijo activo001() {
		ActivoFijo activo = new ActivoFijo();
		activo.setId(1L);
		activo.setActualizado(new Date());
		activo.setCreado(new Date());
		activo.setActualizadoPor("1");
		activo.setCreadoPor("1");
		activo.setDescripcion(null);
		activo.setNombre("maquinaria");
		activo.setEstado("activo");
        return activo;
    }

    public static ActivoFijo activo002() {
    	ActivoFijo activo = new ActivoFijo();
		activo.setId(2L);
		activo.setActualizado(new Date());
		activo.setCreado(new Date());
		activo.setActualizadoPor("2");
		activo.setCreadoPor("2");
		activo.setDescripcion(null);
		activo.setNombre("Bienes inmuebles");
		activo.setEstado("activo");
        return activo;
    }
}
