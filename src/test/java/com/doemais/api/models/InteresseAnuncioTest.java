package com.doemais.api.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;




@SpringBootTest
 class InteresseAnuncioTest {

	@Test
	void TestEquals() {
		UsuarioInteressadoAnuncio interesse = new UsuarioInteressadoAnuncio();
		UsuarioInteressadoAnuncio interesse2 = new UsuarioInteressadoAnuncio();
		
        interesse.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
        interesse2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		
		boolean res = interesse.equals(interesse2);
		
		assertFalse(res);
		
	}
	
	@Test
	 void TestNotEquals() {
		UsuarioInteressadoAnuncio interesse = new UsuarioInteressadoAnuncio();
		UsuarioInteressadoAnuncio interesse2 = new UsuarioInteressadoAnuncio();
		
        interesse.setIdInteresseAnuncio(1);
		interesse2.setIdInteresseAnuncio(2);
		
		boolean res = interesse.equals(interesse2);
		
		assertFalse(res);
	}
	
	@Test
	void TestDataRegistroInteresse() {
		
		UsuarioInteressadoAnuncio interesse = new UsuarioInteressadoAnuncio();
		
		
	    interesse.setIdInteresseAnuncio(1);

	    assertEquals(1, interesse.getIdInteresseAnuncio());
		
	}
	
	
}
