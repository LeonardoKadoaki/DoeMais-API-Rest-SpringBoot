package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IdUsuarioTypeTest {

	private IdUsuarioType idUsuarioType;
	
	@Test
	void TestSettersAndGetters() {
		idUsuarioType = new IdUsuarioType();
		idUsuarioType.setIdUsuario(1);
		
		assertEquals(1, idUsuarioType.getIdUsuario());
	}
	
	
}
