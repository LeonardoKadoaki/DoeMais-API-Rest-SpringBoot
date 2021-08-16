package com.doemais.api.security.dto;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TokenDtoTest {

	private TokenDto dto;
	private TokenDto dto2;

	
	@Test
	void TestConstrutor() {
		dto = new TokenDto("teste", "teste");
		dto2 = new TokenDto("teste", "teste");

		assertNotEquals(dto, dto2);
		assertNotNull(dto.getToken());
		assertNotNull(dto.getType());
	}
	
	
	
}
