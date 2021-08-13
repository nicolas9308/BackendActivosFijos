package co.backend.api.rest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.backend.api.rest.models.entity.ActivoFijo;
import co.test.backend.api.rest.service.IActivoFijoServiceTest;
import co.test.backend.api.rest.service.impl.ActivoFijoServiceTestImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BackendApiRestActivosFijosApplicationTests {

	@MockBean
	private IActivoFijoServiceTest activoFijoService;
	
	@Before
	public void setUp() {
		//MockitoAnnotations.initMocks(this);
		activoFijoService = new ActivoFijoServiceTestImpl();
	}
	
	@Test
	public void contextLoads() throws Exception {
		assertEquals(2,activoFijoService.findAll().size());
	}

	@Test
	public void contextLoads2() throws Exception {
		ActivoFijo activo = activoFijoService.findById(2L);
		assertEquals("maquinaria",activo.getNombre());
	}
}
