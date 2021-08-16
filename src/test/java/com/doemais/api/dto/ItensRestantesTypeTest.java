package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItensRestantesTypeTest {

	private ItensRestantesType itensRestantes;
	
	@Test
	void TestSettersAndGetters() {
		itensRestantes = new ItensRestantesType();
		itensRestantes.setItensRestantes(1);
		itensRestantes.setNextIdAnuncio(1);
		
		assertEquals(1, itensRestantes.getItensRestantes());
		assertEquals(1, itensRestantes.getNextIdAnuncio());
	}
	
}
