package co.backend.api.rest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.backend.api.rest.models.entity.ActivoFijo;
import co.backend.api.rest.models.service.IActivoFijoService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApiRestActivosFijosApplicationTests {

	@Autowired
	private IActivoFijoService activoFijoService;
	
	
	@Test
	public void contextLoads() throws Exception {
		assertEquals(3,activoFijoService.findAll().size());
	}

	@Test
	public void contextLoads2() throws Exception {
		ActivoFijo activo = activoFijoService.findById(2L);
		assertEquals("maquinaria",activo.getNombre());
	}
}
