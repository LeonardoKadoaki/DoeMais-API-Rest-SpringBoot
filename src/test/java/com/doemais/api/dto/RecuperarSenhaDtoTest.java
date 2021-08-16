package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecuperarSenhaDtoTest {

	private RecuperarSenhaDto dto;
	private RecuperarSenhaDto dto2;

	
	@Test
	void TestSetterAndGetters() {
	dto = new RecuperarSenhaDto();
	dto.setEmail("teste@gmail.com");
	
	assertEquals("teste@gmail.com", dto.getEmail());
		
	}
	
	@Test
	void TestConstrutor() {
	dto = new RecuperarSenhaDto("teste@gmail.com");
	dto2 = new RecuperarSenhaDto("teste@gmail.com");

	assertNotEquals(dto, dto2);
		
	}
	
}
