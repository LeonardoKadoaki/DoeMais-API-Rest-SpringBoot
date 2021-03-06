package com.doemais.api.dto;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailAddressTypeTest {

	private EmailAddressType emailAddress;
	
	@Test
	void SetterAndGettersTest() {
		emailAddress = new EmailAddressType();
		emailAddress.setEmail("teste@gmail.com");
		
		assertEquals("teste@gmail.com", emailAddress.getEmail());
	}
	
}
