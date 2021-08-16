package com.doemais.api.models;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ResetarSenhaTokenTest {

	@Test
	void TestConstrutor() throws ParseException {
		Auth auth = new Auth();
		DateFormat formato2 = new SimpleDateFormat("dd/MM/yyyy");
		Date data2 = formato2.parse("05/02/1998");
		ResetarSenhaToken resetar = new ResetarSenhaToken(1L, "teste", auth,  data2);
		
		assertEquals(1, resetar.getId().longValue());
		assertEquals("teste", resetar.getToken());
		assertEquals(auth, resetar.getAuth());
		assertEquals(data2, resetar.getExpiryDate());
		
		
	}
	
}
