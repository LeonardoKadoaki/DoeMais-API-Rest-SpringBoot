package com.doemais.api.models;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EmailTest {

	
	@Test
	void TestHashCode() {
		Usuario user = new Usuario();
		Usuario user2 = new Usuario();
		Email email = new Email();
		Email email2 = new Email();
		email.setAssunto("teste");
		email.setCorpoEmail("teste");
		email.setDataEnvioEmail(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		email.setIdEmail(1);
		email.setUsuarioDest(user);
		email.setUsuarioRemet(user2);
		
		email2.setAssunto("teste");
		email2.setCorpoEmail("teste");
		email2.setDataEnvioEmail(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		email2.setIdEmail(1);
		email2.setUsuarioDest(user);
		email2.setUsuarioRemet(user2);
		
		assertEquals(email.hashCode(), email2.hashCode());

	}
}
