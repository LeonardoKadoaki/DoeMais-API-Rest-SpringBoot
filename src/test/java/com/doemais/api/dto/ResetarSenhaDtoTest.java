package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResetarSenhaDtoTest {

	private ResetarSenhaDto resetar;
	private ResetarSenhaDto resetar2;

	@Test
	void TestSetterAndGetters() {
		resetar = new ResetarSenhaDto();
		
		resetar.setConfirmPassword("teste");
		resetar.setPassword("teste");
		resetar.setToken("teste");
		
		assertEquals("teste", resetar.getConfirmPassword());
		assertEquals("teste", resetar.getPassword());
		assertEquals("teste", resetar.getToken());
	}
	
	
	@Test
	void TestConstrutor() {
		resetar = new ResetarSenhaDto("teste", "teste", "teste");
		resetar2 = new ResetarSenhaDto("teste", "teste", "teste");

		assertNotEquals(resetar, resetar2);

	}
	
}
