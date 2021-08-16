package com.doemais.api.models;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;




@SpringBootTest
 class UsuarioMedalhaTest {

	private UsuarioMedalha usuarioMedalha = new UsuarioMedalha();
	
	@Test
	 void TestUsuarioDataRegistro() throws ParseException {
		
		usuarioMedalha.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		
		assertNotNull(usuarioMedalha.getDataRegistro());
		
	}
	
	
	@Test
	 void TestEquals() throws ParseException {
		UsuarioMedalha usuarioMedalha2 = new UsuarioMedalha();
	
		usuarioMedalha.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuarioMedalha2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		
		boolean res = usuarioMedalha.equals(usuarioMedalha2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestNotEquals() throws ParseException {
		UsuarioMedalha usuarioMedalha2 = new UsuarioMedalha();

		usuarioMedalha.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuarioMedalha2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		
		boolean res = usuarioMedalha.equals(usuarioMedalha2);
		
		assertTrue(res);
	}
	
	@Test
	 void TestHashCode() throws ParseException {
		UsuarioMedalha usuarioMedalha2 = new UsuarioMedalha();
		
		usuarioMedalha.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuarioMedalha.setIdUsuarioMedalha(1);
		usuarioMedalha2.setDataRegistro(LocalDateTime.of(LocalDate.now(), LocalTime.now()));
		usuarioMedalha2.setIdUsuarioMedalha(1);
		assertEquals(usuarioMedalha.hashCode(), usuarioMedalha2.hashCode());
		
		
	}
	
	@Test
	void TestConstrutor() {
		Usuario user = new Usuario();
		Medalha medalha = new Medalha();
		UsuarioMedalha usuarioMedalha = 
				new UsuarioMedalha(LocalDateTime.of(LocalDate.now(), LocalTime.now()), user, medalha);
		
		assertEquals(usuarioMedalha.getDataRegistro(), usuarioMedalha.getDataRegistro());
		assertEquals(user, usuarioMedalha.getUsuario());
		assertEquals(medalha, usuarioMedalha.getMedalha());
	}
	
	@Test
	void TestToString() {
		Usuario user = new Usuario();
		Medalha medalha = new Medalha();
		UsuarioMedalha usuarioMedalha = 
				new UsuarioMedalha(LocalDateTime.of(LocalDate.now(), LocalTime.now()), user, medalha);
		assertNotNull(usuarioMedalha.toString());
		
	}
	
	
}
